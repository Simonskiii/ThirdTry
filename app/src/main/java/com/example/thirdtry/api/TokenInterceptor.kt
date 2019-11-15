package com.example.thirdtry.api

import android.content.Context
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.Response


class TokenInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code() == 400) {
        } else if (response.code() == 404) {
        }

        return response
    }

}


