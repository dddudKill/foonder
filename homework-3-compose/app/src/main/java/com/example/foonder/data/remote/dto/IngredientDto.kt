package com.example.foonder.data.remote.dto

import com.example.foonder.domain.models.Ingredient

data class IngredientDto(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String
) {
    fun toIngredient(): Ingredient {
        return Ingredient(
            id = id,
            image = image,
            localizedName = localizedName,
            name = name
        )
    }
}