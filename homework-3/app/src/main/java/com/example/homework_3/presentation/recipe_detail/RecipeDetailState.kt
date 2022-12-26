package com.example.homework_3.presentation.recipe_detail

import com.example.homework_3.domain.models.recipe_information.RecipeDetail

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: RecipeDetail? = null,
    val error: String = ""
)
