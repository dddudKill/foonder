package com.example.homework_2.api

import com.example.homework_2.GalleryItem
import com.google.gson.annotations.SerializedName

class GiphyResponse {
    //lateinit var gifs: GifsResponse
    @SerializedName("data")
    lateinit var galleryItems: List<GalleryItem>
}
