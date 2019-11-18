package com.example.thirdtry.base

import com.google.gson.annotations.SerializedName

data class BaseDataResult<T>(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("msg")
    var message: String?,
    @SerializedName("success")
    var success: String?,
    @SerializedName("error")
    var error: String?,
    @SerializedName("data")
    var subjects: T
)