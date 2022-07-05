package com.app.android_clean_architecture_assignment.domain.meal.usecase

import com.app.android_clean_architecture_assignment.data.remote.entity.MealApiResponse
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import com.app.android_clean_architecture_assignment.domain.common.ErrorTransformer
import com.app.android_clean_architecture_assignment.domain.common.base.BaseUseCase
import io.reactivex.Single
import javax.inject.Inject

class MealUseCase @Inject constructor(
    private val mealRepository: MealRepository,
    errorTransformer: ErrorTransformer<MealApiResponse>
) : BaseUseCase<MealApiResponse>(errorTransformer) {

    override fun buildSingle(data: Map<String, Any?>): Single<MealApiResponse> {
        return mealRepository.getMealData()
    }

}