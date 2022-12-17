package com.example.foonder.domain.models

import com.example.foonder.domain.models.AnalyzedInstruction
import com.example.foonder.domain.models.ExtendedIngredient


data class RecipeDetail (

    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
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