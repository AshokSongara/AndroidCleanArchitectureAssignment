package com.app.android_clean_architecture_assignment.domain.meal.entity

import com.app.android_clean_architecture_assignment.presentation.model.MealModel
import com.google.gson.annotations.SerializedName

class MealEntity {

    @SerializedName("strCategory")
    lateinit var name: String

    @SerializedName("strCategoryThumb")
    lateinit var mealUrl: String
}

fun MealEntity.transformMealDisplay(): MealModel {
    return MealModel(
        name,
        mealUrl
    )
}

fun ArrayList<MealEntity>.transformMealDisplayList(): ArrayList<MealModel> {
    val mealList = ArrayList<MealModel>()
    this.forEach {
        mealList.add(
            MealModel(
                it.name,
                it.mealUrl
            )
        )
    }
    return mealList
}