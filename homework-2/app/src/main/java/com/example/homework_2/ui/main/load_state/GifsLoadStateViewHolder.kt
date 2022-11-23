package com.example.homework_2.ui.main.load_state

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homework_2.R

class GifsLoadStateViewHolder(
    view: View,
    retry: () -> Unit
) : RecyclerView.ViewHolder(view) {
    private val progressBar: ProgressBar = itemView.findViewById(R.id.load_state_progress)
    private val errorMsg: TextView = itemView.findViewById(R.id.load_state_errorMessage)
    private val retry: Button = itemView.findViewById<Button>(R.id.load_state_retry)
        .also { it.setOnClickListener { retry.invoke() } }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }
        progressBar.visibility = toVisibility(loadState is LoadState.Loading)
        retry.visibility = toVisibility(loadState !is LoadState.Loading)
        errorMsg.visibility = toVisibility(loadState !is LoadState.Loading)
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        layoutParams.isFullSpan = true
    }

    private fun toVisibility(constraint: Boolean): Int = if (constraint) {
        View.VISIBLE
    } else {
        View.GONE
    }
}