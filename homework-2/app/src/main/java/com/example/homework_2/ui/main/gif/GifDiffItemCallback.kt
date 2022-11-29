package com.example.homework_2.ui.main.gif

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_2.objects.Gif

class GifDiffItemCallback : DiffUtil.ItemCallback<Gif>() {
    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.gifId() == newItem.gifId()
    }

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.gifUrl() == newItem.gifUrl()
    }

}