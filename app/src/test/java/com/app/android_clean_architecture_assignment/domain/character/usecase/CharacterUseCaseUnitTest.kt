package com.app.android_clean_architecture_assignment.domain.character.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.android_clean_architecture_assignment.data.common.exceptions.AppHttpException
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import com.app.android_clean_architecture_assignment.domain.common.ErrorTransformer
import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import com.app.android_clean_architecture_assignment.presentation.character.gson
import com.app.android_clean_architecture_assignment.presentation.common.RxImmediateSchedulerRule
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

    // output
    private val characterApiResponse by lazy {
        CharacterApiResponse().apply {
            data = CharacterApiResponse().data
        }
    }

    private val characterError by lazy {
        CharacterApiResponse().apply {
            data = CharacterApiResponse().data
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

    @Test
    fun testCharacterUseCase_error() {
        val responseBody = mock<ResponseBody>()
        whenever(responseBody.string()).thenReturn(Gson().toJson(characterError))
        val response = Response.error<CharacterApiResponse>(401, responseBody)
        val httpException = HttpException(response)
        whenever(characterRepository.getCharacterData())
            .thenReturn(Single.error(httpException))
        characterUseCase.execute()
            .test()
            .assertSubscribed()
            .assertFailure(AppHttpException.UnauthorizedException::class.java)
    }
}