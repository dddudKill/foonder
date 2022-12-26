package com.example.homework_3.data.remote.dto.recipes_by_ingredients

import com.example.homework_3.domain.models.recipes_by_ingredients.MissedIngredient

data class MissedIngredientDto(
    val aisle: String,
    val amount: Double,
    val extendedName: String,
    val id: Int,
    val image: String,
    val meta: List<String>,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String,
    val unitLong: String,
    val unitShort: String
) {
    fun toMissedIngredient(): MissedIngredient {
        return MissedIngredient(
            id = id,
            image = image,
            name = name,
            amount = amount
        )
    }
}