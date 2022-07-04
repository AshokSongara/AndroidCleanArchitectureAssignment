package com.app.android_clean_architecture_assignment.domain.meal.repository

import com.app.android_clean_architecture_assignment.data.local.model.MealLocal
import com.app.android_clean_architecture_assignment.data.remote.entity.MealApiResponse
import io.reactivex.Single

interface MealRepository {
    fun getMealData(): Single<MealApiResponse>
    fun getLocalMealData(): List<MealLocal>
    fun saveAllLocalData(meals : List<MealLocal>)
}
