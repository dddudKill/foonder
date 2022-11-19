package com.example.lecture05.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lecture05.R
import com.example.lecture05.objects.Cat

class CatAdapter: ListAdapter<Cat, CatViewHolder>(CatDiffitemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, parent)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(Cat)
    }

}