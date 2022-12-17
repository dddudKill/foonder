package com.example.foonder.data.remote.dto

import com.example.foonder.domain.models.Step

data class StepDto(
    val equipment: List<EquipmentDto>,
    val ingredients: List<IngredientDto>,
    val length: LengthDto,
    val number: Int,
    val step: String
) {
    fun toStep(): Step {
        return Step(
            equipment = equipment.map { it.toEquipment() },
            ingredients = ingredients.map { it.toIngredient() },
            length = length.toLength(),
            number = number,
            step = step
        )
    }
}