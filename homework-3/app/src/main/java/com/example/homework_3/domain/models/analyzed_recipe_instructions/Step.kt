package com.example.homework_3.domain.models.analyzed_recipe_instructions

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<IngredientInStep>,
    val length: Length?,
    val number: Int,
    val step: String
)