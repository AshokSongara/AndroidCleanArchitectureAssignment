package com.app.android_clean_architecture_assignment.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CharacterEntity(

    @SerializedName("_id")
    val id: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("imageUrl")
    val imageUrl: String?,

    @SerializedName("url")
    val url: String?
)

