package com.app.android_clean_architecture_assignment.presentation.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
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
import org.mockito.junit.MockitoRule

class CharacterViewModelUnitTest {
    //output value
    private val successResponse: Resource<MutableList<CharacterModel>> =
        Resource(Status.SUCCESS, chratcerDomainResponse())
    private val errorCharacterResponse: Resource<Throwable> =
        Resource(Status.ERROR, throwable = Exception())
    private val loadingCharacterResponse: Resource<MutableList<CharacterModel>> =
        Resource(Status.LOADING)

    @get:Rule
    val mockitoRule: MockitoRule? = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    //mock dependencies
    private val characterUseCase = mock<CharacterUseCase>()
    private val characterViewModel by lazy { CharacterViewModel(characterUseCase) }
    private var characterObserver = mock<Observer<Resource<MutableList<CharacterModel>>>>()

    @Captor
    var argumentCaptor: ArgumentCaptor<Resource<MutableList<CharacterModel>>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        reset(characterUseCase)
    }

    @Test
    fun fetchCharacters_success() {
        val delayer = PublishSubject.create<Resource<MutableList<CharacterModel>>>()

        stub_fetchCharacters_success(delayer)

        characterViewModel.characterLiveEvent.observeForever(characterObserver)
        characterViewModel.loadPage()

        argumentCaptor?.apply {
            Mockito.verify(characterObserver).onChanged(capture())
            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(loadingCharacterResponse))
            )
        }
        delayer.onComplete()

        argumentCaptor?.apply {
            Mockito.verify(characterObserver, Mockito.times(2)).onChanged(capture())
            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(successResponse))
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
                CoreMatchers.`is`(Gson().toJson(errorCharacterResponse))
            )
        }
    }

    //----------------------------stubbing-------------------------------------//

    private fun stub_fetchCharacters_success(delayer: PublishSubject<Resource<MutableList<CharacterModel>>>) {
        whenever(
            characterUseCase.execute()
        ).thenReturn(
            Single.create<MutableList<CharacterModel>> { emitter ->
                try {
                    emitter.onSuccess(chratcerDomainResponse())
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