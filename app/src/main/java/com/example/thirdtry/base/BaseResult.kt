package com.example.thirdtry.base

import com.google.gson.annotations.SerializedName

data class BaseResult<T>(
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("result")
    var subjects: T
)