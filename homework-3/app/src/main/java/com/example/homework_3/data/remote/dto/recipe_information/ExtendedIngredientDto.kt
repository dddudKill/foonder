package com.example.homework_3.data.remote.dto.recipe_information

import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient


data class ExtendedIngredientDto(
    val aisle: String?,
    val amount: Double?,
    val consistency: String?,
    val id: Int,
    val image: String?,
    val measure: MeasureDto?,
    val meta: List<String>?,
    val name: String,
    val nameClean: String?,
    val original: String?,
    val originalName: String?,
    val unit: String?
) {
    fun toExtendedIngredient(): ExtendedIngredient {
        return ExtendedIngredient(
            aisle = aisle?.split(";")?.first() ?: "Other",
            id = id,
            name = name,
            image = image ?: ""
        )
    }
}