package com.app.android_clean_architecture_assignment.presentation.meal

import com.app.android_clean_architecture_assignment.common.extensions.setError
import com.app.android_clean_architecture_assignment.common.extensions.setLoading
import com.app.android_clean_architecture_assignment.common.extensions.setSuccess
import com.app.android_clean_architecture_assignment.domain.meal.entity.Meal
import com.app.android_clean_architecture_assignment.domain.meal.usecase.MealUseCase
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel
import com.app.android_clean_architecture_assignment.presentation.common.base.SingleLiveEvent
import javax.inject.Inject

class MealViewModel @Inject constructor(
    private var mealUseCase: MealUseCase
) : BaseViewModel() {

    internal val mealLiveEvent = SingleLiveEvent<Resource<ArrayList<Meal>>>()

    override fun loadPage(multipleTimes: Boolean): Boolean {
        fetchMeals()
        return super.loadPage(multipleTimes)
    }

    private fun fetchMeals() {
        mealLiveEvent.setLoading()
        mealUseCase.execute()
            .subscribe({
                mealLiveEvent.setSuccess(it.data)
            }, {
                mealLiveEvent.setError(it)
            }).collect()
    }
}