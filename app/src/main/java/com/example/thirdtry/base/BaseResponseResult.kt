package com.example.thirdtry.base

import com.example.thirdtry.ui.activity.login.LoginModel

//程序中通过所得response自行判断是否成功的类
data class BaseResponseResult(
    val success: String? = null,
    val error: String? = null
)

