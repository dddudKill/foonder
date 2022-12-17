package com.example.foonder.data.remote.dto

import com.example.foonder.domain.models.Length

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