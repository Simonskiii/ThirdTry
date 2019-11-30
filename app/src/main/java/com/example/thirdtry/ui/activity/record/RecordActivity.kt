package com.example.thirdtry.ui.activity.record

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.thirdtry.R
import com.example.thirdtry.databinding.ActivityArticleDetialBinding
import com.example.thirdtry.model.Article
import com.example.thirdtry.ui.activity.articleDetail.ArticleDetialActivity
import com.example.thirdtry.ui.activity.articleDetail.ArticleDetialViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class RecordActivity : AppCompatActivity() {
    companion object {
        lateinit var instance: RecordActivity
    }
    private var records = mutableListOf<Article>()
    private var type: String? = null
    private lateinit var viewModel: RecordViewModel
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        EventBus.getDefault().register(this)
    }
    private val mObserver: Observer<Article> by lazy {
        Observer<Article> {
            if (it == null) {
                Toast.makeText(this, "网络开小差了，请稍后", Toast.LENGTH_SHORT).show()
                return@Observer
            } else {
                val a = it.id
                binding.data = it
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onMessageEvent(event: String) {
        this.type = event
        viewModel = ViewModelProviders.of(this).get(RecordViewModel::class.java)
        val s = getSharedPreferences("loginToken", Context.MODE_PRIVATE)
        token = s.getString("token", "")!!
        when(type){
            "fav" -> viewModel.getFav(token).observe(this, mObserver)
            "history" -> viewModel.getHistory(token).observe(this, mObserver)
        }

    }
}
