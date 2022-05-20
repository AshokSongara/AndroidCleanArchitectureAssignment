package com.app.android_clean_architecture_assignment.domain.meal.repository

import com.app.android_clean_architecture_assignment.data.meal.entity.MealApiResponse
import io.reactivex.Single

interface MealRepository {
    fun getMealData(): Single<MealApiResponse>
}
