package com.example.thirdtry.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.thirdtry.BASE_URL1
import com.example.thirdtry.view.CircleImageView

object BindingAdapters{
    @BindingAdapter("bind:imageUrl")
    @JvmStatic fun imageUrl(view: ImageView?, s: String?) {
        Glide.with(view!!.context).load(BASE_URL1+s).into(view)
    }
    @BindingAdapter("bind:imageUrll")
    @JvmStatic fun imageUrl(view: CircleImageView?, s: String?) {
        Glide.with(view!!.context).load(s).into(view)
    }
}
