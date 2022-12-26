package com.example.homework_3.domain.models.recipes_by_ingredients

import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient


data class Aisle(
    val title: String,
    val ingredients: Set<ExtendedIngredient>,
    var expand: Boolean = false
)