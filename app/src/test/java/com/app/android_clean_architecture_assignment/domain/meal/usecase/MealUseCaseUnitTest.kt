package com.app.android_clean_architecture_assignment.domain.meal.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.android_clean_architecture_assignment.data.common.exceptions.AppHttpException
import com.app.android_clean_architecture_assignment.data.meal.entity.MealApiResponse
import com.app.android_clean_architecture_assignment.domain.common.ErrorTransformer
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import com.app.android_clean_architecture_assignment.presentation.common.RxImmediateSchedulerRule
import com.app.android_clean_architecture_assignment.presentation.meal.gson
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

class MealUseCaseUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    private val mealRepository = mock<MealRepository>()
    private val mealUseCase by lazy {
        MealUseCase(mealRepository, ErrorTransformer(gson))
    }

    // output
    private val mealResponse by lazy {
        MealApiResponse().apply {
            data = MealApiResponse().data
        }
    }

    private val mealError by lazy {
        MealApiResponse().apply {
            data = MealApiResponse().data
        }
    }

    @Before
    fun setUp() {
        reset(mealRepository)
    }

    @Test
    fun testMealUseCase_success() {
        whenever(mealRepository.getMealData())
            .thenReturn(Single.just(mealResponse))
        mealUseCase.execute().test()
            .assertSubscribed()
            .assertValue {
                it.data.size > 0
            }
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun testMealUseCase_error() {
        val responseBody = mock<ResponseBody>()
        whenever(responseBody.string()).thenReturn(Gson().toJson(mealError))
        val response = Response.error<MealApiResponse>(401, responseBody)
        val httpException = HttpException(response)
        whenever(mealRepository.getMealData())
            .thenReturn(Single.error(httpException))
        mealUseCase.execute()
            .test()
            .assertSubscribed()
            .assertFailure(AppHttpException.UnauthorizedException::class.java)
    }
}