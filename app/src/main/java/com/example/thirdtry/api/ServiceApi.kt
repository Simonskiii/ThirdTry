package com.example.thirdtry.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thirdtry.base.BaseResult
import com.example.thirdtry.model.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Description: https://www.jianshu.com/p/a7e51129b042
 * Data：2019/1/26
 * Actor:Steven
 */

interface ServiceApi {
    @GET("getSingleJoke")
    fun getSingleJoke(@Query("sid") sid: String): MutableLiveData<BaseResult<Article>>
    @GET("getJoke")
    fun getJoke(@Query("page") page: Int, @Query("count") count:Int,
                 @Query("type") type: String): MutableLiveData<BaseResult<MutableList<Article>>>

    @GET("articles")
    fun getArticles(): MutableLiveData<MutableList<Article>>
}