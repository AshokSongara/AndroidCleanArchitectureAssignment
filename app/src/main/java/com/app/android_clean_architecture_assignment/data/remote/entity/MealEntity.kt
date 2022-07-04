package com.app.android_clean_architecture_assignment.data.remote.entity

import com.app.android_clean_architecture_assignment.domain.model.MealModel
import com.google.gson.annotations.SerializedName

class MealEntity {

    @SerializedName("idCategory")
    lateinit var id: String

    @SerializedName("strCategory")
    lateinit var name: String

    @SerializedName("strCategoryThumb")
    lateinit var mealUrl: String

    @SerializedName("strCategoryDescription")
    lateinit var mealDescription: String
}

fun ArrayList<MealEntity>.transformMealDisplayList(): ArrayList<MealModel> {
    val mealList = ArrayList<MealModel>()
    this.forEach {
        mealList.add(
            MealModel(
                it.id,
                it.name,
                it.mealUrl,
                it.mealDescription
            )
        )
    }
    return mealList
}