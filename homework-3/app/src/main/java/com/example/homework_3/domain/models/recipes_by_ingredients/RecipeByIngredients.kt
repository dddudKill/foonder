package com.example.homework_3.domain.models.recipes_by_ingredients

data class RecipeByIngredients(
    val id: Int,
    val image: String,
    val missedIngredientCount: Int,
    val missedIngredients: List<MissedIngredient>,
    val title: String,
    val unusedIngredients: List<UnusedIngredient>,
    val usedIngredientCount: Int,
    val usedIngredients: List<UsedIngredient>
)