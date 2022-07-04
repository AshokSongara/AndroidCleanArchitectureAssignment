package com.app.android_clean_architecture_assignment.data.remote.entity

import com.google.gson.annotations.SerializedName

class MealApiResponse {
    @SerializedName("categories")
    lateinit var data: ArrayList<MealEntity>
}
