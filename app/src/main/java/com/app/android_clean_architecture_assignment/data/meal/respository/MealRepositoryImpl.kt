package com.app.android_clean_architecture_assignment.data.meal.respository

import com.app.android_clean_architecture_assignment.data.meal.entity.MealApiResponse
import com.app.android_clean_architecture_assignment.domain.meal.repository.MealRepository
import io.reactivex.Single
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi
) : MealRepository {

    override fun getMealData(): Single<MealApiResponse> {
        return mealApi.mealCategory()
    }
}