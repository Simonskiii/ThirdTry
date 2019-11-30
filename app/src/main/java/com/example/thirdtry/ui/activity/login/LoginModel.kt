package com.example.thirdtry.ui.activity.login

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("id")
    val id: String? = null
)