package com.app.android_clean_architecture_assignment.domain.meal.entity

import com.google.gson.annotations.SerializedName

class MealEntity {

    @SerializedName("strCategory")
    lateinit var name: String

    @SerializedName("strCategoryThumb")
    lateinit var mealUrl: String
}