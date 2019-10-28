package com.example.thirdtry.ui.article

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.thirdtry.R

object BindingAdapters{
    @BindingAdapter("bind:imageUrl")
    @JvmStatic fun imageUrl(view: ImageView, s: Int) {
        Glide.with(view.context).load(s).into(view)
    }
}