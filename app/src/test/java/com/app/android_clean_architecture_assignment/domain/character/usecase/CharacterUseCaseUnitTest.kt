package com.app.android_clean_architecture_assignment.domain.character.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.android_clean_architecture_assignment.data.common.exceptions.AppHttpException
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import com.app.android_clean_architecture_assignment.domain.common.ErrorTransformer
import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import com.app.android_clean_architecture_assignment.presentation.character.characterResponse
import com.app.android_clean_architecture_assignment.presentation.character.gson
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.RxImmediateSchedulerRule
import com.app.android_clean_architecture_assignment.presentation.common.Status
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class CharacterUseCaseUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    private val characterRepository = mock<CharacterRepository>()

    private val characterUseCase by lazy {
        CharacterUseCase(characterRepository, ErrorTransformer(gson))
    }

    private val errorCharacterResponse: Resource<Throwable> =
        Resource(Status.ERROR, throwable = Exception())

    // output
    private val characterApiResponse by lazy {
        CharacterApiResponse().apply {
            data = characterResponse().data
        }
    }

    @Before
    fun setUp() {
        reset(characterRepository)
    }

    @Test
    fun testCharacterUseCase_success() {
        whenever(characterRepository.getCharacterData())
            .thenReturn(Single.just(characterApiResponse))
        characterUseCase.execute().test()
            .assertSubscribed()
            .assertValue {
                it.data.size > 0
            }
            .assertComplete()
            .assertNoErrors()
    }
}