package com.app.android_clean_architecture_assignment.domain.model

data class MealModel(
    val id: String,
    val name: String?,
    val mealUrl: String?,
    val mealDescription: String?
)