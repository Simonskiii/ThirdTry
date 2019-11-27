package com.example.thirdtry.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseBindingAdapter<T, B : ViewDataBinding>(var items: MutableList<T>, private var layoutRes: Int) : RecyclerView.Adapter<BaseBindingAdapter<T, B>.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<B>(inflater, layoutRes, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class ViewHolder(private val binding: B) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            bindItem(binding, item)
            binding.executePendingBindings()
        }
    }

    abstract fun bindItem(binding: B, item: T)
}