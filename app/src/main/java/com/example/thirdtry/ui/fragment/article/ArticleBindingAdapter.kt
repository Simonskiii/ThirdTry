package com.example.thirdtry.ui.fragment.article

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.thirdtry.R
import com.example.thirdtry.databinding.ArticleItemsLayoutBinding
import com.example.thirdtry.base.BaseBindingAdapter
import com.example.thirdtry.model.Article
import com.example.thirdtry.ui.activity.articleDetail.ArticleDetialActivity
import org.greenrobot.eventbus.EventBus


class ArticleBindingAdapter(
    items: MutableList<Article>
) : BaseBindingAdapter<Article, ArticleItemsLayoutBinding>(items, R.layout.article_items_layout) {
    override fun bindItem(binding: ArticleItemsLayoutBinding, item: Article) {
        binding.data = item
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(it.context, items[position].name, Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context, ArticleDetialActivity::class.java)
            it.context.startActivity(intent)
            EventBus.getDefault().postSticky(items[position].id.toString())
        })
    }
}