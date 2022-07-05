package com.app.android_clean_architecture_assignment.domain.meal.usecase

import com.app.android_clean_architecture_assignment.data.local.model.MealLocal
import com.app.android_clean_architecture_assignment.domain.common.base.BaseCoroutineUsecase
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import javax.inject.Inject

class SaveDBUsecase @Inject constructor(
    private val mealRepository: MealRepository
) : BaseCoroutineUsecase<Unit, MealLocal>() {

    override suspend fun execute(params: MealLocal){
        return mealRepository.saveAllLocalData(params)
    }

}