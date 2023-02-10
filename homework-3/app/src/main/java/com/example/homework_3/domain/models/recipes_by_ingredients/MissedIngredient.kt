package com.example.homework_3.domain.models.recipes_by_ingredients

data class MissedIngredient(
    val amount: Double,
    val id: Int,
    val image: String,
    val name: String,
)