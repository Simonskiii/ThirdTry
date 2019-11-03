package com.example.thirdtry.ui.fragment.article

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters{
    @BindingAdapter("bind:imageUrl")
    @JvmStatic fun imageUrl(view: ImageView, s: Int) {
        Glide.with(view.context).load(s).into(view)
    }
}