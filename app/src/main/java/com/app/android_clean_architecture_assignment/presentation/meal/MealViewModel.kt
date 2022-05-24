package com.app.android_clean_architecture_assignment.presentation.meal

import com.app.android_clean_architecture_assignment.common.extensions.setError
import com.app.android_clean_architecture_assignment.common.extensions.setLoading
import com.app.android_clean_architecture_assignment.common.extensions.setSuccess
import com.app.android_clean_architecture_assignment.domain.meal.usecase.MealUseCase
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel
import com.app.android_clean_architecture_assignment.presentation.common.base.SingleLiveEvent
import com.app.android_clean_architecture_assignment.presentation.mapper.MealDisplayMapper
import com.app.android_clean_architecture_assignment.presentation.model.MealModel
import javax.inject.Inject

class MealViewModel @Inject constructor(
    private var mealUseCase: MealUseCase
) : BaseViewModel() {

    private val _mealLiveEvent = SingleLiveEvent<Resource<ArrayList<MealModel>>>()
    val mealLiveEvent: SingleLiveEvent<Resource<ArrayList<MealModel>>> = _mealLiveEvent

    var dataList = ArrayList<MealModel>()

    override fun loadPage(multipleTimes: Boolean): Boolean {
        fetchMeals()
        return super.loadPage(multipleTimes)
    }

    private fun fetchMeals() {
        _mealLiveEvent.setLoading()
        mealUseCase.execute()
            .subscribe({
                it.data.map { meal ->
                    dataList.add(MealDisplayMapper().transformMealDisplay(meal))
                }
                _mealLiveEvent.setSuccess(dataList)
            }, {
                _mealLiveEvent.setError(it)
            }).collect()
    }
}