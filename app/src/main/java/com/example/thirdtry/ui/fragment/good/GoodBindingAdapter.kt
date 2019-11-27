package com.example.thirdtry.ui.fragment.good

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.thirdtry.R
import com.example.thirdtry.base.BaseBindingAdapter
import com.example.thirdtry.databinding.GoodItemsLayoutBinding
import com.example.thirdtry.model.Good
import com.example.thirdtry.ui.activity.articleDetail.ArticleDetialActivity
import org.greenrobot.eventbus.EventBus

class GoodBindingAdapter (
    items: MutableList<Good>
) : BaseBindingAdapter<Good, GoodItemsLayoutBinding>(items, R.layout.good_items_layout) {
    override fun bindItem(binding: GoodItemsLayoutBinding, item: Good) {
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