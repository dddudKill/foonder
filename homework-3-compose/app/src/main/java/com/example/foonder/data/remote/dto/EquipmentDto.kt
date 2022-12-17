package com.example.foonder.data.remote.dto

import com.example.foonder.domain.models.Equipment

data class EquipmentDto(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String
) {
    fun toEquipment(): Equipment {
        return Equipment(
            id = id,
            image = image,
            localizedName = localizedName,
            name = name
        )
    }
}