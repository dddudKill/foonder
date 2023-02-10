package com.example.homework_3.data.remote.dto.recipe_information


data class StepDto(
    val equipment: List<EquipmentDto>,
    val ingredients: List<IngredientDto>,
    val length: LengthDto,
    val number: Int,
    val step: String
)