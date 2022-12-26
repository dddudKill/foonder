package com.example.homework_3.data.remote.dto.analyzed_recipe_instructions

import com.example.homework_3.domain.models.analyzed_recipe_instructions.Equipment
import com.example.homework_3.domain.models.analyzed_recipe_instructions.Temperature


data class EquipmentDto(
    val id: Int,
    val image: String?,
    val name: String,
    val temperature: TemperatureDto?
) {
    fun toEquipment(): Equipment {
        return Equipment(
            id = id,
            image = image ?: "apple.png",
            name = name,
            temperature = temperature?.toTemperature() ?: Temperature(number = 24.0, unit = "celsius")
        )
    }
}