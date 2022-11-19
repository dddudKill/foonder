package com.example.homework_2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class GifsGalleryViewModel : ViewModel() {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    init {
        galleryItemLiveData = GiphyFetcher().fetchGifs()
    }
}