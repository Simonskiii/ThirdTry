package com.example.thirdtry.ui.fragment.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.R
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.base.BaseResult
import com.example.thirdtry.model.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.random.Random

class ArticleViewModel : ViewModel() {
    fun getInTheaters(): MutableLiveData<BaseResult<MutableList<Article>>> =
        RetrofitClient.serviceApi.getJoke(1,10,"video")
    fun getArticles(): MutableLiveData<MutableList<Article>> =
        RetrofitClient.serviceApi.getArticles()
    val data : MutableLiveData<BaseResult<MutableList<Article>>> = MutableLiveData()
}
