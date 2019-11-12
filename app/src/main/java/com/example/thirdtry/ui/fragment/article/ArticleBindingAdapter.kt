package com.example.thirdtry.ui.fragment.article

import android.view.View
import android.widget.Toast
import com.example.thirdtry.R
import com.example.thirdtry.databinding.ArticleItemsLayoutBinding
import com.example.thirdtry.base.BaseBindingAdapter
import com.example.thirdtry.model.Article


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
        })
    }


}