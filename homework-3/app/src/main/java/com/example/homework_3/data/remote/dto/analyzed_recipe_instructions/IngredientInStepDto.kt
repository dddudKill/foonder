package com.example.homework_3.data.remote.dto.analyzed_recipe_instructions

import com.example.homework_3.domain.models.analyzed_recipe_instructions.IngredientInStep

data class IngredientInStepDto(
    val id: Int,
    val image: String,
    val name: String
) {
    fun toIngredientInStep(): IngredientInStep {
        return IngredientInStep(
            id = id,
            image = image,
            name = name
        )
    }
}