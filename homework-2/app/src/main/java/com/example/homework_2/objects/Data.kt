package com.example.homework_2.objects

import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("data") var gifs: List<Gif> = emptyList()

    @SerializedName("pagination") var pagination: Pagination? = null
}