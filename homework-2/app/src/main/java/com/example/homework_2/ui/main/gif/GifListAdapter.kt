package com.example.homework_2.ui.main.gif

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.homework_2.R
import com.example.homework_2.objects.Gif

class GifListAdapter(val callback: (gif: Gif) -> Unit) : PagingDataAdapter<Gif, GifViewHolder>(GifDiffItemCallback()) {

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, callback) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gif, parent, false)
        return GifViewHolder(view)
    }
}