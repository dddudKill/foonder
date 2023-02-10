package com.example.homework_3.domain.models.analyzed_recipe_instructions

data class RecipeInstruction(
    val name: String,
    val steps: List<Step>
)