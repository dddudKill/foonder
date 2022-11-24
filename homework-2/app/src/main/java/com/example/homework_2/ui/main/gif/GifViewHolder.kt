package com.example.homework_2.ui.main.gif

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.homework_2.R
import com.example.homework_2.objects.Gif

class GifViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    protected val image by lazy { view.findViewById<ImageView>(R.id.image) }

    fun bind(gif: Gif, callback: (gif: Gif) -> Unit) {

        val url = gif.gifUrl()
        Glide.with(view)
            .load(url)
            .override(Target.SIZE_ORIGINAL)
            .into(image)
        view.setOnClickListener{ callback(gif) }
    }
}