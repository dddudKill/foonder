package com.example.homework_2.objects

import com.google.gson.annotations.SerializedName

class Gif {
    var id = ""
    var title = ""
    @SerializedName("images") var images: Images = Images(Original(""))

    fun gifId() = id
    fun gifTitle() = title
    fun gifUrl() = images.orig.url
}

data class Images (@SerializedName("original") var orig: Original)

data class Original (var url: String)