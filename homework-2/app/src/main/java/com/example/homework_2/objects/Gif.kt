package com.example.homework_2.objects

import com.google.gson.annotations.SerializedName

class Gif {
    private var id = ""
    private var title = ""
    @SerializedName("images") private var images: Images = Images(Original(url = ""))

    fun gifId() = id
    fun gifTitle() = title
    fun gifUrl() = images.orig.url
    fun gifWidth() = images.orig.width
    fun gifHeight() = images.orig.height
}

data class Images (@SerializedName("original") var orig: Original)

data class Original (
    @SerializedName("webp") var url: String = "",
    @SerializedName("width") var width: String = "",
    @SerializedName("height") var height: String = ""
    )