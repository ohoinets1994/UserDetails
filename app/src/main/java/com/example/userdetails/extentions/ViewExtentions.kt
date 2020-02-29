package com.example.userdetails.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(url: String) {
    Glide
        .with(context)
        .load(url)
        .apply(RequestOptions().circleCrop())
        .into(this)
}