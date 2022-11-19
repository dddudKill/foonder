package com.example.homework_2.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GiphyApi {

    @GET(
        "v1/gifs/trending" +
                "?api_key=GkXGkAFHmtNjfPZoy18GALzVNOOreyeY"
    )
    fun fetchGifs(): Call<GiphyResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>
}