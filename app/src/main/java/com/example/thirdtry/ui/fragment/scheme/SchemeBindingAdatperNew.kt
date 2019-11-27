package com.example.thirdtry.ui.fragment.scheme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thirdtry.R
import com.example.thirdtry.databinding.CommodityItemsLayoutBinding
import com.example.thirdtry.databinding.TipItemsLayoutBinding
import com.example.thirdtry.model.Commodity
import com.example.thirdtry.model.Tip


class SchemeBindingAdatperNew(
    var tips: MutableList<Tip>,
    var commodities: MutableList<Commodity>,
    private var layoutRes: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val tipTYPE = 1
    private val commodityTYPE = 2
    private val titleTYPE = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            titleTYPE -> return ViewHolderTitle(inflater.inflate(R.layout.scheme_title, parent, false))
            tipTYPE -> {
                val binding = DataBindingUtil.inflate<TipItemsLayoutBinding>(
                    inflater,
                    layoutRes,
                    parent,
                    false
                )
                return ViewHolderTips(binding)
            }
            commodityTYPE -> {
                val binding = DataBindingUtil.inflate<CommodityItemsLayoutBinding>(
                    inflater,
                    layoutRes,
                    parent,
                    false
                )
                return ViewHolderCommodities(binding)
            }
            else -> return ViewHolderTitle(inflater.inflate(R.layout.scheme_title, parent, false))
        }
    }

    override fun getItemCount() = getTipsCount() + getCommoditiesCount() + 2
    private fun getTipsCount() = tips.size
    private fun getCommoditiesCount() = commodities.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && position == getTipsCount() + 1) {
            //头部View
            titleTYPE
        } else if (position <= getTipsCount() && position != 0) {
            //底部View
            tipTYPE
        } else {
            commodityTYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderTitle -> holder.title.text = "日常方案"
            is ViewHolderTips -> holder.bind(tips[position - 1])
            is ViewHolderCommodities -> holder.bind(commodities[position - 2 - getTipsCount()])
        }
    }


    inner class ViewHolderTips(private val binding: TipItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tip) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    inner class ViewHolderCommodities(private val binding: CommodityItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Commodity) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    inner class ViewHolderTitle(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_tv) as TextView
    }


}