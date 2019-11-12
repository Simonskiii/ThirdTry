package com.example.thirdtry.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.R
<<<<<<< Updated upstream:app/src/main/java/com/example/thirdtry/ui/article/ArticleViewModel.kt
import kotlin.collections.HashMap
import kotlin.random.Random

class ArticleViewModel : ViewModel() {
    var lineData : MutableLiveData<MutableList<Article>> = MutableLiveData()
    val names =
        arrayOf("北极熊", "犀牛", "花豹", "白马", "小鹦鹉", "袋鼠", "狐狸", "小猫咪", "哈巴狗", "蜥蜴", "大熊猫", "蚂蚁"
        )
    val pics = intArrayOf(
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px,
        R.drawable.ic_account_circle_24px
    )

    fun addData(){
        for (i in 0..29) {
            val random = Random
            val n = random.nextInt(pics.size)
            val article = Article(names[n], pics[n])
            lineData.value?.add(article)
        }
    }



    // TODO: Implement the ViewModel
=======
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
>>>>>>> Stashed changes:app/src/main/java/com/example/thirdtry/ui/fragment/article/ArticleViewModel.kt
}
