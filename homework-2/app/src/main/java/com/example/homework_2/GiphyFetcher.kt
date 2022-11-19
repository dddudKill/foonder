package com.example.homework_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework_2.api.GiphyApi
import com.example.homework_2.api.GiphyResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "GiphyFetcher"
const val BASE_URL = "https://api.giphy.com/"

class GiphyFetcher {

    private val giphyApi: GiphyApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        giphyApi = retrofit.create(GiphyApi::class.java)
    }

    fun fetchGifs(): LiveData<List<GalleryItem>> {
        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        val giphyRequest: Call<GiphyResponse> = giphyApi.fetchGifs()

        giphyRequest.enqueue(object : Callback<GiphyResponse> {

            override fun onFailure(call: Call<GiphyResponse>, t: Throwable) {
                Log.e(TAG, "Failed to  fetch gifs", t)
            }

            override fun onResponse(
                call: Call<GiphyResponse>,
                response: Response<GiphyResponse>
            ) {
                Log.d(TAG, "Response received")
                val giphyResponse: GiphyResponse? = response.body()
                var galleryItems: List<GalleryItem> = giphyResponse?.galleryItems
                    ?: mutableListOf()
                galleryItems = galleryItems.filterNot {
                    it.images.original.url.isBlank()
                }
                responseLiveData.value = galleryItems
            }
        })

        return  responseLiveData
    }

    @WorkerThread
    fun fetchGif(url: String): Bitmap? {
        val response: Response<ResponseBody> = giphyApi.fetchUrlBytes(url).execute()
        val bitmap = response.body()?.byteStream()?.use(BitmapFactory::decodeStream)
        Log.i(TAG, "Decoded bitmap=$bitmap from Response=$response")
        return bitmap
    }

}
