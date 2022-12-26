package com.example.homework_3.data.remote.dto.analyzed_recipe_instructions

import com.example.homework_3.domain.models.analyzed_recipe_instructions.RecipeInstruction

data class RecipeInstructionDto(
    val name: String?,
    val steps: List<StepDto>
) {
    fun toRecipeInstruction(): RecipeInstruction {
        return RecipeInstruction(
            name = name ?: "",
            steps = steps.map { it.toStep() }
        )
    }
}