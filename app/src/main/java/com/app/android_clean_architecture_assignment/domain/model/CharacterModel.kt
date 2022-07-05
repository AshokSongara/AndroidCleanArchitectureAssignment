package com.app.android_clean_architecture_assignment.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val id: String,
    val name: String?,
    val imageUrl: String?,
    val url: String?
) : Parcelable