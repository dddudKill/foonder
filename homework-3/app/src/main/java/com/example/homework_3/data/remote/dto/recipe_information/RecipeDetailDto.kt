package com.example.homework_3.data.remote.dto.recipe_information

import com.example.homework_3.domain.models.recipe_information.RecipeDetail

data class RecipeDetailDto(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstructionDto>,
    val cheap: Boolean,
    val cookingMinutes: Int,
    val creditsText: String,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredientDto>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Int,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val license: String,
    val lowFodmap: Boolean,
    val occasions: List<String>,
    val originalId: Any,
    val preparationMinutes: Int,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int,
    val winePairing: WinePairingDto
) {
    fun toRecipeDetail(): RecipeDetail {
        return RecipeDetail(
            aggregateLikes = aggregateLikes,
            cuisines = cuisines,
            dairyFree = dairyFree,
            diets = diets,
            dishTypes = dishTypes,
            id = id,
            image = image,
            instructions = instructions,
            pricePerServing = pricePerServing,
            readyInMinutes = readyInMinutes,
            servings = servings,
            summary = summary,
            title = title,
            vegan = vegan,
            vegetarian = vegetarian
        )
    }
}

