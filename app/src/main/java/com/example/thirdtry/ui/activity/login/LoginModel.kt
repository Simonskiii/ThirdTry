package com.example.thirdtry.ui.activity.login

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("msg")
    val error: String? = null,
    @SerializedName("user")
    val user: String? = null
)