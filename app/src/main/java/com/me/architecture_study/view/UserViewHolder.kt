package com.me.architecture_study.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.me.architecture_study.R
import com.me.architecture_study.databinding.ItemUserPreviewBinding
import com.me.architecture_study.model.User

class UserViewHolder(private var binding: ItemUserPreviewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        binding.user = user
        binding.executePendingBindings()

    }
}