package com.example.thirdtry.ui.fragment.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.R
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
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


    interface Api {
        @GET("getSingleJoke")
        fun category(@Query("sid") sid: String): Call<article1>
//                     @Query("count") count: Int,
//                     @Query("type") type: String) : Call<article1>
    }


    // TODO: Implement the ViewModel
}
