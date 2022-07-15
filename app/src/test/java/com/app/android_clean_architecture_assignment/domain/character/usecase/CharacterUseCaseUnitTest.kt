package com.app.android_clean_architecture_assignment.domain.character.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import com.app.android_clean_architecture_assignment.domain.common.ErrorTransformer
import com.app.android_clean_architecture_assignment.presentation.character.chratcerDomainResponse
import com.app.android_clean_architecture_assignment.presentation.character.gson
import com.app.android_clean_architecture_assignment.presentation.common.RxImmediateSchedulerRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
        chratcerDomainResponse()
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
                it.isNotEmpty()
            }
            .assertComplete()
            .assertNoErrors()
    }
}