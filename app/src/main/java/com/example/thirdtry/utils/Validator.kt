package com.example.thirdtry.utils

import java.util.regex.Pattern

class Validator {
    companion object{
        val REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
        fun isEmail(email: String): Boolean {
            return Pattern.matches(REGEX_EMAIL, email)
        }
    }
}