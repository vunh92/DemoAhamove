package com.vunh.android.demoahamove.data.model

import com.google.gson.annotations.SerializedName

data class StatusModel(
    @SerializedName("type")
    var type: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("code")
    var code: Int,
    @SerializedName("error")
    var error: Boolean,
)
