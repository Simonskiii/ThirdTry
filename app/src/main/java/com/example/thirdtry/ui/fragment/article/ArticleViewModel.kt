package com.example.thirdtry.ui.fragment.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.model.Article

class ArticleViewModel : ViewModel() {
    fun getArticles(): MutableLiveData<BaseDataResult<MutableList<Article>>> =
        RetrofitClient.serviceApi.getArticles()
}
