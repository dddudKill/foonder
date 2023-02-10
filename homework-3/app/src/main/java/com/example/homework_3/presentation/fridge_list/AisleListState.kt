package com.example.homework_3.presentation.fridge_list

import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient
import com.example.homework_3.domain.models.recipes_by_ingredients.Aisle

data class AisleListState(
    val isLoading: Boolean = false,
    val aisles: Map<String, List<ExtendedIngredient>> = emptyMap(),
    val error: String = ""
)
