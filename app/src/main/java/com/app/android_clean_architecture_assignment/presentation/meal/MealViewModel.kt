package com.app.android_clean_architecture_assignment.presentation.meal

import androidx.lifecycle.viewModelScope
import com.app.android_clean_architecture_assignment.common.setError
import com.app.android_clean_architecture_assignment.common.setLoading
import com.app.android_clean_architecture_assignment.common.setSuccess
import com.app.android_clean_architecture_assignment.domain.meal.usecase.FetchDataUseCase
import com.app.android_clean_architecture_assignment.domain.meal.usecase.MealUseCase
import com.app.android_clean_architecture_assignment.domain.meal.usecase.SaveDBUsecase
import com.app.android_clean_architecture_assignment.domain.model.MealModel
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel
import com.app.android_clean_architecture_assignment.presentation.common.base.SingleLiveEvent
import com.app.android_clean_architecture_assignment.presentation.mapper.MealDisplayMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private var mealUseCase: MealUseCase,
    private var fetchUseCase: FetchDataUseCase,
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

    fun getMealData() {
        viewModelScope.launch {
            fetchUseCase.invoke(
                scope = viewModelScope,
                params = Unit
            ) { result ->
                result.result({
                    Timber.i("Total Size${it.size}")
                }, {
                    Timber.e("Error")
                })
            }
        }
    }

}