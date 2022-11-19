package com.example.homework_2

import com.google.gson.annotations.SerializedName

data class GalleryItem(
    var title: String = "",
    var id: String = "",
    @SerializedName("images")
    var images: Images
)

data class Images (
    var original: Original
)

data class Original (
    var url: String = ""
)


