package com.kc.casestudyapp.ui.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kc.casestudyapp.R

const val ANIMATION_TIME = 500
fun ImageView.loadImageUsingGlide(imageUrl: String) {
    Glide.with(context).load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade(ANIMATION_TIME))
        .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_image))
        .into(this)
}