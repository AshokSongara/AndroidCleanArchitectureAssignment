package com.app.android_clean_architecture_assignment.data.common.entity

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String
)