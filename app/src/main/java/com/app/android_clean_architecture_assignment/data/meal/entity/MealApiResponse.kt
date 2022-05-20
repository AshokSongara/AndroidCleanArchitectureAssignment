package com.app.android_clean_architecture_assignment.data.meal.entity

import com.google.gson.annotations.SerializedName
import com.app.android_clean_architecture_assignment.domain.meal.entity.Meal

class MealApiResponse {
    @SerializedName("categories")
    lateinit var data: ArrayList<Meal>
}
