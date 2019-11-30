package com.example.thirdtry.ui.activity.articleDetail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thirdtry.R
import org.greenrobot.eventbus.EventBus
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.model.Good
import com.example.thirdtry.databinding.ActivityArticleDetialBinding
import com.example.thirdtry.model.Article
import com.example.thirdtry.ui.fragment.good.GoodBindingAdapter
import kotlinx.android.synthetic.main.activity_article_detial.*
import kotlinx.android.synthetic.main.good_fragment.*


class ArticleDetialActivity : AppCompatActivity() {
    companion object {
        lateinit var instance: ArticleDetialActivity
    }

    private var id: String? = null
    private lateinit var viewModel: ArticleDetialViewModel
    private lateinit var token: String
    private lateinit var binding: ActivityArticleDetialBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detial)
        EventBus.getDefault().register(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detial)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        comments_rv.layoutManager=layoutManager
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onMessageEvent(event: String) {
        this.id = event
        viewModel = ViewModelProviders.of(this).get(ArticleDetialViewModel::class.java)
        val s = getSharedPreferences("loginToken", Context.MODE_PRIVATE)
        token = s.getString("token", "")!!
        viewModel.getArticleDetail(token, this.id!!).observe(this, mObserver)
    }
}
