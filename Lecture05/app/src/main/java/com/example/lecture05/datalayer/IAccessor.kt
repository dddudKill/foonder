package com.example.lecture05.datalayer

import com.example.lecture05.objects.Cat
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.logging.Logger

interface IAccessor {
    @GET("/api/cats")
    @Headers("X-User-Agent: meow")
    fun getCats(@Query("skip") offset: Int, @Query("limit") limit: Int) : List<Cat>

    companion object {
        suspend fun create(): IAccessor {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }



            val client = OkHttpClient.Builder().apply {
                addNetworkInterceptor(loggingInterceptor)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                baseUrl("https://cataas.com/")
            }.build()

            return retrofit.create(IAccessor::class.java)
        }
    }
}