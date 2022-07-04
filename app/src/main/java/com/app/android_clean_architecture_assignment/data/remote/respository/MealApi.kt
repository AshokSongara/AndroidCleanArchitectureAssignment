package com.app.android_clean_architecture_assignment.data.remote.respository

import com.app.android_clean_architecture_assignment.data.remote.entity.MealApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MealApi {
    @GET("categories.php")
    fun mealCategory(): Single<MealApiResponse>
}