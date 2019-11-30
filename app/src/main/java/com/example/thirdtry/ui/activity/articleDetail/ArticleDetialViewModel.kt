package com.example.thirdtry.ui.activity.articleDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.model.Article
import com.example.thirdtry.model.Good

class ArticleDetialViewModel: ViewModel() {
    fun getArticleDetail(token: String, id: String): MutableLiveData<Article> =
        RetrofitClient.serviceApi.getArticleDetail("JWT $token", id)
}