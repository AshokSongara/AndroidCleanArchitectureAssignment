package com.app.android_clean_architecture_assignment.data.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class BaseResponse {

    @Expose
    @SerializedName("meta")
    lateinit var meta: MetaResponse
}