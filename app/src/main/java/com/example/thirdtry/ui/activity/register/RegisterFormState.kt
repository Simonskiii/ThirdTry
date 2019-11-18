package com.example.thirdtry.ui.activity.register

class RegisterFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val emailError: Int? = null,
    val codeError: Int? = null,
    val isDataValid: Boolean = false
)