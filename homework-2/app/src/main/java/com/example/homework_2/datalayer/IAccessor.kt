package com.example.homework_2.datalayer

import com.example.homework_2.objects.Data
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val API_KEY = "GkXGkAFHmtNjfPZoy18GALzVNOOreyeY"

interface IAccessor {
    @GET("/v1/gifs/trending")
    @Headers("X-User-Agent: GIF")
    suspend fun getData(@Query("offset") offset: Int, @Query("limit") limit: Int): Data


    companion object {
        fun create(baseUrl: String): IAccessor {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val apiInterceptor = Interceptor {
                val originalRequest = it.request()
                val newHttpUrl = originalRequest.url.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val newRequest = originalRequest.newBuilder()
                    .url(newHttpUrl)
                    .build()
                it.proceed(newRequest)
            }

            val client = OkHttpClient.Builder().apply {
                addNetworkInterceptor(loggingInterceptor)
                addNetworkInterceptor(apiInterceptor)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(baseUrl)
            }.build()

            return retrofit.create(IAccessor::class.java)
        }
    }
}