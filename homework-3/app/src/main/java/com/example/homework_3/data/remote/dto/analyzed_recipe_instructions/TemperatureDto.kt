package com.example.homework_3.data.remote.dto.analyzed_recipe_instructions

import com.example.homework_3.domain.models.analyzed_recipe_instructions.Temperature

data class TemperatureDto(
    val number: Double,
    val unit: String
) {
    fun toTemperature(): Temperature {
        return Temperature(
            number = number,
            unit = unit
        )
    }
}