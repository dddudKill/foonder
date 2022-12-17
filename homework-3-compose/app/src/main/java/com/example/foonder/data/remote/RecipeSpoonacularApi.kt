package com.example.foonder.data.remote

import com.example.foonder.data.remote.dto.RecipeDetailDto
import com.example.foonder.data.remote.dto.RecipeResponseDto
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeSpoonacularApi {

    @GET("recipes/random")
    suspend fun getRecipes(@Query("number") number: Int = 2, @Query("tags") tags: String? = null): RecipeResponseDto

    @GET("recipes/{id}/information")
    suspend fun getRecipeById(@Path("id") id: Int): RecipeDetailDto


    companion object {

        const val API_KEY = "9694c65a6cb4489d9f737d7b12eedf7f"

        fun create(baseUrl: String): RecipeSpoonacularApi {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val apiInterceptor = Interceptor {
                val originalRequest = it.request()
                val newHttpUrl = originalRequest.url.newBuilder()
                    .addQueryParameter("apiKey", API_KEY)
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

            return retrofit.create(RecipeSpoonacularApi::class.java)
        }
    }

}