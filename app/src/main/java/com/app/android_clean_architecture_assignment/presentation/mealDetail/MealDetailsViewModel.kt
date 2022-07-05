package com.app.android_clean_architecture_assignment.presentation.mealDetail

import androidx.lifecycle.viewModelScope
import com.app.android_clean_architecture_assignment.domain.meal.usecase.SaveDBUsecase
import com.app.android_clean_architecture_assignment.domain.model.MealModel
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel
import com.app.android_clean_architecture_assignment.presentation.mapper.MealDisplayMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private var saveDBUseCase: SaveDBUsecase
) : BaseViewModel() {

    fun insertMealItem(mealList: MealModel) {
        viewModelScope.launch {
            saveDBUseCase.invoke(
                scope = viewModelScope,
                params = MealDisplayMapper().transformMealDomainToLocal(mealList)
            ) { result ->
                result.result({
                    Timber.i("Successfully Saved Data")
                }, {
                    Timber.e("Error")
                })
            }
        }
    }
}