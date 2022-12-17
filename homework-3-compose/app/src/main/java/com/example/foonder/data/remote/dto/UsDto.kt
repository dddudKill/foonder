package com.example.foonder.data.remote.dto

import com.example.foonder.domain.models.Us

data class UsDto(
    val amount: Double,
    val unitLong: String,
    val unitShort: String
) {
    fun toUs(): Us {
        return Us(
            amount = amount,
            unitLong = unitLong,
            unitShort = unitShort
        )
    }
}