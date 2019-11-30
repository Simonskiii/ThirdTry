package com.example.thirdtry.api

import androidx.lifecycle.MutableLiveData
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.base.BaseResult
import com.example.thirdtry.model.*
import com.example.thirdtry.ui.activity.login.LoginModel
import retrofit2.http.*
import java.util.*

/**
 * Description: https://www.jianshu.com/p/a7e51129b042
 * Data：2019/1/26
 * Actor:Steven
 */

interface ServiceApi {
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
    fun register(
        @Field("name") name: String, @Field("password") password: String,
        @Field("email") email: String, @Field("code") code: String
    ): MutableLiveData<BaseResult>

    @GET("g/articles/")
    fun getArticles(@Header("Authorization") Authorization: String): MutableLiveData<BaseDataResult<MutableList<Article>>>

    @GET("g/history/")
    fun getHistory(@Header("Authorization") Authorization: String): MutableLiveData<BaseDataResult<MutableList<Record>>>

    @GET("g/fav/")
    fun getFav(@Header("Authorization") Authorization: String): MutableLiveData<BaseDataResult<MutableList<Record>>>

    @GET("g/goods/")
    fun getGoods(@Header("Authorization") Authorization: String): MutableLiveData<BaseDataResult<MutableList<Good>>>

    @GET("g/goods/{id}/")
    fun getGoodDetail(@Header("Authorization") Authorization: String, @Path("id") Id: String)
            : MutableLiveData<Good>

    @GET("g/articles/{id}/")
    fun getArticleDetail(@Header("Authorization") Authorization: String, @Path("id") Id: String)
            : MutableLiveData<Article>

    @GET("g/scheme/")
    fun getScheme(@Header("Authorization") Authorization: String)
            : MutableLiveData<BaseDataResult<Scheme>>


    @PUT("/g/user/{id}/")
    @FormUrlEncoded
    fun editInformation(
        @Header("Authorization") Authorization: String, @Path("id") Id: String,
        @Field("name") name: String, @Field("gender") gender: String,
        @Field("birthday") birthday: String, @Field("typ") type: String
    ): MutableLiveData<Information>

    @GET("/g/user/{id}/")
    fun getInformation(@Header("Authorization") Authorization: String, @Path("id") Id: String)
            : MutableLiveData<Information>



}
