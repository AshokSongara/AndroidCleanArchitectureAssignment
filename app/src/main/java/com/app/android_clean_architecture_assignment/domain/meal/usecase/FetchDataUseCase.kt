package com.app.android_clean_architecture_assignment.domain.meal.usecase

import com.app.android_clean_architecture_assignment.data.local.model.MealLocal
import com.app.android_clean_architecture_assignment.domain.common.base.BaseCoroutineUsecase
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import javax.inject.Inject


class FetchDataUseCase @Inject constructor(
    private val mealRepository: MealRepository
) : BaseCoroutineUsecase<List<MealLocal>, Unit>() {

    override suspend fun execute(params: Unit): List<MealLocal> {
        return mealRepository.getLocalMealData()
    }

}