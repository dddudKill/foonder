package com.example.homework_3.domain.models.recipe_information


data class RecipeDetail (

    val aggregateLikes: Int,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val id: Int,
    val image: String,
    val instructions: String,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
)