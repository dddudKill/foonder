package com.example.homework_3.domain.models.recipe_information

data class ExtendedIngredient(
    val aisle: String,
    val id: Int,
    val image: String,
    val name: String,
    var isSelected: Boolean = false
)
