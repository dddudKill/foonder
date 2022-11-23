package com.example.homework_2.ui.main.load_state

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.homework_2.R

class GifsLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<GifsLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: GifsLoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) : GifsLoadStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.load_state_item, parent, false)
        return GifsLoadStateViewHolder(view, retry)
    }
}