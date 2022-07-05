package com.app.android_clean_architecture_assignment.data.remote.entity

import com.google.gson.annotations.SerializedName

class CharacterApiResponse {
    @SerializedName("data")
    lateinit var data: ArrayList<CharacterEntity>
}
