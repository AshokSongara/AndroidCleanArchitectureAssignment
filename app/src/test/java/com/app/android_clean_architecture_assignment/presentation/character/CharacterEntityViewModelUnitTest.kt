package com.app.android_clean_architecture_assignment.presentation.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import com.app.android_clean_architecture_assignment.data.remote.entity.transformCharacterDisplayList
import com.app.android_clean_architecture_assignment.domain.character.usecase.FetchLocalDataUseCase
import com.app.android_clean_architecture_assignment.domain.character.usecase.CharacterUseCase
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.RxImmediateSchedulerRule
import com.app.android_clean_architecture_assignment.presentation.common.Status
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

class CharacterEntityViewModelUnitTest {
    //output value
    private val successLoginResponse: Resource<ArrayList<CharacterModel>> =
        Resource(Status.SUCCESS, characterResponse().data.transformCharacterDisplayList())
    private val errorLoginResponse: Resource<Throwable> =
        Resource(Status.ERROR, throwable = Exception())
    private val loadingLoginResponse: Resource<ArrayList<CharacterModel>> = Resource(Status.LOADING)

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    //mock dependencies
    private val characterUseCase = mock<CharacterUseCase>()
    private val fetchUseCase = mock<FetchLocalDataUseCase>()
    private val characterViewModel by lazy { CharacterViewModel(characterUseCase,fetchUseCase) }
    private var characterObserver = mock<Observer<Resource<ArrayList<CharacterModel>>>>()

    @Captor
    var argumentCaptor: ArgumentCaptor<Resource<ArrayList<CharacterModel>>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        reset(characterUseCase)
    }

    @Test
    fun fetchCharacters_success() {
        val delayer = PublishSubject.create<Resource<ArrayList<CharacterModel>>>()

        stub_fetchCharacters_success(delayer)

        characterViewModel.characterLiveEvent.observeForever(characterObserver)
        characterViewModel.loadPage()

        argumentCaptor?.apply {
            Mockito.verify(characterObserver).onChanged(capture())
            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(loadingLoginResponse))
            )
        }
        delayer.onComplete()

        argumentCaptor?.apply {
            Mockito.verify(characterObserver, Mockito.times(2)).onChanged(capture())
            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(successLoginResponse))
            )
        }
    }

    @Test
    fun fetchCharacters_error() {
        stub_fetchCharacters_error()

        characterViewModel.characterLiveEvent.observeForever(characterObserver)
        characterViewModel.loadPage()

        argumentCaptor?.apply {
            Mockito.verify(characterObserver, Mockito.times(2)).onChanged(capture())

            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(errorLoginResponse))
            )
        }
    }

    //----------------------------stubbing-------------------------------------//

    private fun stub_fetchCharacters_success(delayer: PublishSubject<Resource<ArrayList<CharacterModel>>>) {
        whenever(
            characterUseCase.execute()
        ).thenReturn(
            Single.create<CharacterApiResponse> { emitter ->
                try {
                    emitter.onSuccess(characterResponse())
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }.delaySubscription(delayer)
        )
    }

    private fun stub_fetchCharacters_error() {
        whenever(
            characterUseCase.execute()
        ).thenReturn(
            Single.error(Throwable())
        )
    }
}