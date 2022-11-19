package com.example.homework_2

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "MainFragment"

class MainFragment : Fragment() {
    private lateinit var gifsGalleryViewModel: GifsGalleryViewModel
    private lateinit var gifRecyclerView: RecyclerView
    private lateinit var thumbnailDownloader: ThumbnailDownloader<GifHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true

        gifsGalleryViewModel = ViewModelProvider(this)[GifsGalleryViewModel::class.java]

        val responseHandler = Handler()
        thumbnailDownloader = ThumbnailDownloader(responseHandler) { gifHolder, bitmap ->
            val drawable = BitmapDrawable(resources, bitmap)
            gifHolder.bindDrawable(drawable)
        }
        lifecycle.addObserver(thumbnailDownloader.fragmentLifecycleObserver)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLifecycleOwner.lifecycle.addObserver(thumbnailDownloader.viewLifecycleObserver)
        val view = inflater.inflate(R.layout.fragment_gifs, container, false)

        gifRecyclerView = view.findViewById(R.id.gif_recycler_view)
        gifRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gifsGalleryViewModel.galleryItemLiveData.observe(
            viewLifecycleOwner
        ) { galleryItems ->
            gifRecyclerView.adapter = GifAdapter(galleryItems)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewLifecycleOwner.lifecycle.removeObserver(thumbnailDownloader.viewLifecycleObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(
            thumbnailDownloader.fragmentLifecycleObserver
        )
    }

    private class GifHolder(itemImageView: ImageView) : RecyclerView.ViewHolder(itemImageView) {
        val bindDrawable: (Drawable) -> Unit = itemImageView::setImageDrawable
    }

    private inner class GifAdapter(private val galleryItems: List<GalleryItem>) : RecyclerView.Adapter<GifHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHolder {
            val view = layoutInflater.inflate(
                R.layout.list_item_gallery,
                parent,
                false
            ) as ImageView
            return GifHolder(view)
        }

        override fun onBindViewHolder(holder: GifHolder, position: Int) {
            val galleryItem = galleryItems[position]
            val placeholder: Drawable = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bill_up_close
            ) ?: ColorDrawable()
            holder.bindDrawable(placeholder)
            thumbnailDownloader.queueThumbnail(holder, galleryItem.images.original.url)
        }

        override fun getItemCount(): Int = galleryItems.size

    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
