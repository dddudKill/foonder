package com.example.homework_3.data.remote.dto.recipes_by_ingredients

import com.example.homework_3.domain.models.recipes_by_ingredients.RecipeByIngredients

data class RecipeByIngredientsDto(
    val id: Int,
    val image: String,
    val imageType: String,
    val likes: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<MissedIngredientDto>,
    val title: String,
    val unusedIngredients: List<UnusedIngredientDto>,
    val usedIngredientCount: Int,
    val usedIngredients: List<UsedIngredientDto>
) {
    fun toRecipeByIngredients(): RecipeByIngredients {
        return RecipeByIngredients(
            id = id,
            image = image,
            missedIngredientCount = missedIngredientCount,
            missedIngredients = missedIngredients.map { it.toMissedIngredient() },
            title = title,
            unusedIngredients = unusedIngredients.map { it.toUnusedIngredient() },
            usedIngredientCount = usedIngredientCount,
            usedIngredients = usedIngredients.map { it.toUsedIngredient() },
        )
    }
}