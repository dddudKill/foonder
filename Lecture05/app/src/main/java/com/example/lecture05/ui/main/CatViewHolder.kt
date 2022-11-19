package com.example.lecture05.ui.main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lecture05.R
import com.example.lecture05.objects.Cat

class CatViewHolder(view: View): RecyclerView.ViewHolder(view) {
    protected val image by lazy { view.findViewById<ImageView>(R.id.image) }

    fun bind(cat: Cat) {
        image
    }
}