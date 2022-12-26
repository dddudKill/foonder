package com.example.homework_3.presentation.step_stack

import com.example.homework_3.domain.models.analyzed_recipe_instructions.RecipeInstruction

data class StepStackListState(
    val isLoading: Boolean = false,
    val instruction: RecipeInstruction? = null,
    val error: String = ""
)
