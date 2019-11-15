package com.example.thirdtry.api

import android.media.session.MediaSession
import android.util.Log
import com.example.thirdtry.BASE_URL
import com.example.thirdtry.TAG
import com.example.thirdtry.utils.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.GsonBuilder
import com.google.gson.Gson




class RetrofitClient {
    companion object {
        val serviceApi: ServiceApi by lazy {
            val retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                        Log.i(TAG, message)
                    }).setLevel(HttpLoggingInterceptor.Level.BODY)
                    ).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
            retrofitClient.create(ServiceApi::class.java)
        }
    }
}

