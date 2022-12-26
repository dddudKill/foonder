package com.example.homework_3.data.remote

import com.example.homework_3.data.remote.dto.analyzed_recipe_instructions.RecipeInstructionDto
import com.example.homework_3.data.remote.dto.recipe_information.RecipeDetailDto
import com.example.homework_3.data.remote.dto.recipe_information.RecipeResponseDto
import com.example.homework_3.data.remote.dto.recipes_by_ingredients.RecipeByIngredientsDto
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
    suspend fun getRecipes(@Query("number") number: Int, @Query("tags") tags: String?): RecipeResponseDto

    @GET("recipes/{id}/information")
    suspend fun getRecipeById(@Path("id") id: Int): RecipeDetailDto

    @GET("recipes/findByIngredients")
    suspend fun getRecipesByIngredients(@Query("ingredients") ingredients: List<String>): List<RecipeByIngredientsDto>

    @GET("recipes/{id}/analyzedInstructions")
    suspend fun getRecipeAnalyzedInstructions(@Path("id") id: Int): List<RecipeInstructionDto>


    companion object {

        private const val API_KEY = "9694c65a6cb4489d9f737d7b12eedf7f"

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