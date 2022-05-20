package com.app.android_clean_architecture_assignment.presentation.meal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import com.app.android_clean_architecture_assignment.data.meal.entity.MealApiResponse
import com.app.android_clean_architecture_assignment.domain.meal.entity.Meal
import com.app.android_clean_architecture_assignment.domain.meal.usecase.MealUseCase
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.RxImmediateSchedulerRule
import com.app.android_clean_architecture_assignment.presentation.common.Status
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

class MealViewModelUnitTest {
    //output value
    private val successLoginResponse: Resource<ArrayList<Meal>> =
        Resource(Status.SUCCESS, mealResponse().data)
    private val errorLoginResponse: Resource<Throwable> =
        Resource(Status.ERROR, throwable = Exception())
    private val loadingLoginResponse: Resource<ArrayList<Meal>> = Resource(Status.LOADING)

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    //mock dependencies
    private val mealUseCase = mock<MealUseCase>()
    private val mealViewModel by lazy { MealViewModel(mealUseCase) }
    private var mealObserver = mock<Observer<Resource<ArrayList<Meal>>>>()

    @Captor
    var argumentCaptor: ArgumentCaptor<Resource<ArrayList<Meal>>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        reset(mealUseCase)
    }

    @Test
    fun fetchMeals_success() {
        val delayer = PublishSubject.create<Resource<ArrayList<Meal>>>()

        stub_fetchMeals_success(delayer)

        mealViewModel.mealLiveEvent.observeForever(mealObserver)
        mealViewModel.loadPage()

        argumentCaptor?.apply {
            Mockito.verify(mealObserver).onChanged(capture())
            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(loadingLoginResponse))
            )
        }
        delayer.onComplete()

        argumentCaptor?.apply {
            Mockito.verify(mealObserver, Mockito.times(2)).onChanged(capture())
            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(successLoginResponse))
            )
        }
    }

    @Test
    fun fetchMeals_error() {
        stub_fetchMeals_error()

        mealViewModel.mealLiveEvent.observeForever(mealObserver)
        mealViewModel.loadPage()

        argumentCaptor?.apply {
            Mockito.verify(mealObserver, Mockito.times(2)).onChanged(capture())

            MatcherAssert.assertThat(
                Gson().toJson(value),
                CoreMatchers.`is`(Gson().toJson(errorLoginResponse))
            )
        }
    }

    //----------------------------stubbing-------------------------------------//

    private fun stub_fetchMeals_success(delayer: PublishSubject<Resource<ArrayList<Meal>>>) {
        whenever(
            mealUseCase.execute()
        ).thenReturn(
            Single.create<MealApiResponse> { emitter ->
                try {
                    emitter.onSuccess(mealResponse())
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }.delaySubscription(delayer)
        )
    }

    private fun stub_fetchMeals_error() {
        whenever(
            mealUseCase.execute()
        ).thenReturn(
            Single.error(Throwable())
        )
    }
}