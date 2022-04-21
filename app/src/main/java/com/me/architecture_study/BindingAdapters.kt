package com.me.architecture_study

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.me.architecture_study.model.User
import com.me.architecture_study.view.UserAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setTitle")
fun bindTitle(textView: TextView, title: String?) {
    title?.let {
        textView.text = it[0].uppercase() + it.substring(1)
    }
}