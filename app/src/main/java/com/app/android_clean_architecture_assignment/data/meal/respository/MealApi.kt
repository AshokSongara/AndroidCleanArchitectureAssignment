package com.app.android_clean_architecture_assignment.data.meal.respository

import com.app.android_clean_architecture_assignment.data.meal.entity.MealApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MealApi {
    @GET("categories.php")
    fun mealCategory(): Single<MealApiResponse>
}