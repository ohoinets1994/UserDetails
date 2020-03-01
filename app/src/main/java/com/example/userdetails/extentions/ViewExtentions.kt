package com.example.userdetails.extentions

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(url: String) {
    Glide
        .with(context)
        .load(url)
        .apply(RequestOptions().circleCrop())
        .into(this)
}

fun RecyclerView.scrollListener(action: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val lastVisible = layoutManager.findLastVisibleItemPosition()
            val threshold = 5

            if (layoutManager.itemCount - lastVisible < threshold) {
                action()
            }
        }
    })
}