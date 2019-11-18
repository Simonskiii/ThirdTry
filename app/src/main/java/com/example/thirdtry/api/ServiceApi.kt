package com.example.thirdtry.api

import androidx.lifecycle.MutableLiveData
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.base.BaseResult
import com.example.thirdtry.model.Article
import com.example.thirdtry.ui.activity.login.LoginModel
import retrofit2.http.*

/**
 * Description: https://www.jianshu.com/p/a7e51129b042
 * Data：2019/1/26
 * Actor:Steven
 */

interface ServiceApi {
    @GET("getSingleJoke")
    fun getSingleJoke(@Query("sid") sid: String): MutableLiveData<BaseDataResult<Article>>

    @GET("getJoke")
    fun getJoke(
        @Query("page") page: Int, @Query("count") count: Int,
        @Query("type") type: String
    ): MutableLiveData<BaseDataResult<MutableList<Article>>>

    @GET("g/articles")
    fun getArticles(): MutableLiveData<BaseDataResult<MutableList<Article>>>

    @POST("jwt-token-auth/")
    @FormUrlEncoded
    fun login(@Field("username") username: String, @Field("password") password: String):
            MutableLiveData<BaseDataResult<LoginModel>>

    @POST("jwt-token-refresh/")
    @FormUrlEncoded
    fun refresh_login(@Field("token") token: String):
            MutableLiveData<BaseDataResult<LoginModel>>

    @POST("/g/verify/")
    @FormUrlEncoded
    fun verify(@Field("email") email: String):
            MutableLiveData<BaseResult>
    @POST("/g/user/")
    @FormUrlEncoded
    fun register(@Field("name") name: String,@Field("password") password: String,
                 @Field("email") email: String,@Field("code") code: String):
            MutableLiveData<BaseResult>
}
