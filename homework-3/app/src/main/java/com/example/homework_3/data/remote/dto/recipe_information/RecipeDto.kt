package com.example.homework_3.data.remote.dto.recipe_information

import com.example.homework_3.domain.models.recipe_information.Recipe

data class RecipeDto(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstructionDto>,
    val cheap: Boolean,
    val cookingMinutes: Int,
    val creditsText: String,
    val cuisines: List<String>?,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>?,
    val extendedIngredients: List<ExtendedIngredientDto>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Int,
    val id: Int?,
    val image: String?,
    val imageType: String,
    val instructions: String,
    val license: String,
    val lowFodmap: Boolean,
    val occasions: List<String>,
    val originalId: Any,
    val preparationMinutes: Int,
    val pricePerServing: Double,
    val readyInMinutes: Int?,
    val servings: Int?,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    val summary: String?,
    val sustainable: Boolean,
    val title: String?,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int
) {
    fun toRecipe(): Recipe {
        return Recipe(
            id = id ?: 0,
            title = title ?: "",
            readyInMinutes = readyInMinutes ?: -1,
            servings = servings ?: -1,
            image = image ?: "",
            summary = summary ?: "",
            cuisines = cuisines ?: emptyList(),
            dishTypes = dishTypes ?: emptyList(),
            extendedIngredients = extendedIngredients.map { it.toExtendedIngredient() }
        )
    }
}

