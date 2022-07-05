package com.app.android_clean_architecture_assignment.presentation.meal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.android_clean_architecture_assignment.data.remote.entity.MealApiResponse
import com.app.android_clean_architecture_assignment.data.remote.entity.transformMealDisplayList
import com.app.android_clean_architecture_assignment.domain.meal.usecase.FetchDataUseCase
import com.app.android_clean_architecture_assignment.domain.meal.usecase.MealUseCase
import com.app.android_clean_architecture_assignment.domain.model.MealModel
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

class MealEntityViewModelUnitTest {
    //output value
    private val successLoginResponse: Resource<ArrayList<MealModel>> =
        Resource(Status.SUCCESS, mealResponse().data.transformMealDisplayList())
    private val errorLoginResponse: Resource<Throwable> =
        Resource(Status.ERROR, throwable = Exception())
    private val loadingLoginResponse: Resource<ArrayList<MealModel>> = Resource(Status.LOADING)

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    //mock dependencies
    private val mealUseCase = mock<MealUseCase>()
    private val fetchUseCase = mock<FetchDataUseCase>()
    private val mealViewModel by lazy { MealViewModel(mealUseCase,fetchUseCase) }
    private var mealObserver = mock<Observer<Resource<ArrayList<MealModel>>>>()

    @Captor
    var argumentCaptor: ArgumentCaptor<Resource<ArrayList<MealModel>>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        reset(mealUseCase)
    }

    @Test
    fun fetchMeals_success() {
        val delayer = PublishSubject.create<Resource<ArrayList<MealModel>>>()

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

    private fun stub_fetchMeals_success(delayer: PublishSubject<Resource<ArrayList<MealModel>>>) {
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