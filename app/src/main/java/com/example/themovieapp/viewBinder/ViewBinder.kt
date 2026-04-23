package com.example.themovieapp.viewBinder

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.bindScrUrl(
    url: String
) {
    Glide.with(this)
    .load(url)
    .centerInside()
    .into(this)
}