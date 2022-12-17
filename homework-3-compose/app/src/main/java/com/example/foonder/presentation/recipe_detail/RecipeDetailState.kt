package com.example.foonder.presentation.recipe_detail

import com.example.foonder.domain.models.RecipeDetail

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: RecipeDetail? = null,
    val error: String = ""
)
