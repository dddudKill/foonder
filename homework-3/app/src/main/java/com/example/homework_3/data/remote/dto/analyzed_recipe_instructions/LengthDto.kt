package com.example.homework_3.data.remote.dto.analyzed_recipe_instructions

import com.example.homework_3.domain.models.analyzed_recipe_instructions.Length

data class LengthDto(
    val number: Int,
    val unit: String
) {
    fun toLength(): Length {
        return Length(
            number = number,
            unit = unit
        )
    }
}