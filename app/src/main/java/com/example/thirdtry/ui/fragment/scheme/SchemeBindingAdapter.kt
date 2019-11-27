package com.example.thirdtry.ui.fragment.scheme

import android.view.View
import android.widget.Toast
import com.example.thirdtry.R
import com.example.thirdtry.base.BaseBindingAdapter
import com.example.thirdtry.databinding.CommodityItemsLayoutBinding
import com.example.thirdtry.databinding.TipItemsLayoutBinding
import com.example.thirdtry.model.Commodity
import com.example.thirdtry.model.Tip
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TipBindingAdapter(
    items: MutableList<Tip>
) : BaseBindingAdapter<Tip, TipItemsLayoutBinding>(
    items,
    R.layout.tip_items_layout
) {
    override fun bindItem(binding: TipItemsLayoutBinding, item: Tip) {
        binding.data = item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            MaterialAlertDialogBuilder(it.context)
                .setMessage(items[position].content)
//                .setPositiveButton(R.string.text_button, null)
//                .setNegativeButton(R.string.text_button, null)
                .show()

        })
    }
}

class CommodityBindingAdapter(
    items: MutableList<Commodity>
) : BaseBindingAdapter<Commodity, CommodityItemsLayoutBinding>(
    items,
    R.layout.commodity_items_layout
) {
    override fun bindItem(binding: CommodityItemsLayoutBinding, item: Commodity) {
        binding.data = item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(it.context, items[position].name, Toast.LENGTH_SHORT).show()
//            val intent = Intent(it.context, ArticleDetialActivity::class.java)
//            it.context.startActivity(intent)
//            EventBus.getDefault().postSticky(items[position].name.toString())
        })
    }
}