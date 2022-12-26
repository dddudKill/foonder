package com.example.homework_3.data.remote.dto.analyzed_recipe_instructions

import com.example.homework_3.domain.models.analyzed_recipe_instructions.Step

data class StepDto(
    val equipment: List<EquipmentDto>?,
    val ingredients: List<IngredientInStepDto>?,
    val length: LengthDto?,
    val number: Int,
    val step: String
) {
    fun toStep(): Step {
        return Step(
            equipment = equipment?.map { it.toEquipment() } ?: emptyList(),
            ingredients = ingredients?.map { it.toIngredientInStep() } ?: emptyList(),
            length = length?.toLength(),
            number = number,
            step = step
        )
    }
}