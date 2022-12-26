package com.example.homework_3.data.remote.dto.recipes_by_ingredients

import com.example.homework_3.domain.models.recipes_by_ingredients.UnusedIngredient

data class UnusedIngredientDto(
    val aisle: String,
    val amount: Double,
    val id: Int,
    val image: String,
    val meta: List<Any>,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String,
    val unitLong: String,
    val unitShort: String
) {
    fun toUnusedIngredient(): UnusedIngredient {
        return UnusedIngredient(
            id = id,
            image = image,
            name = name,
            amount = amount
        )
    }
}