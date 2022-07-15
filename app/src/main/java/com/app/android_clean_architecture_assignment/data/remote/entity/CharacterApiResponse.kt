package com.app.android_clean_architecture_assignment.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CharacterApiResponse(
    @SerializedName("data")
    val data: List<CharacterEntity>
)
