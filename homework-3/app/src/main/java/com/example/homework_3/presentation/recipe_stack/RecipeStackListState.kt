package com.example.homework_3.presentation.recipe_stack

import com.example.homework_3.domain.models.recipes_by_ingredients.RecipeByIngredients

data class RecipeStackListState(
    val isLoading: Boolean = false,
    val recipes: List<RecipeByIngredients> = emptyList(),
    val error: String = ""
)
