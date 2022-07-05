package com.app.android_clean_architecture_assignment.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MealModel(
    val id: String,
    val name: String?,
    val mealUrl: String?,
    val mealDescription: String?
) : Parcelable