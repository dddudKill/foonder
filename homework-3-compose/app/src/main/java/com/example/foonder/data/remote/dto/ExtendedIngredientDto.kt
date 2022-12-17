package com.example.foonder.data.remote.dto

import com.example.foonder.domain.models.ExtendedIngredient

data class ExtendedIngredientDto(
    val aisle: String,
    val amount: Double,
    val consistency: String,
    val id: Int,
    val image: String,
    val measure: MeasureDto,
    val meta: List<String>,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val unit: String
) {
    fun toExtendedIngredient(): ExtendedIngredient {
        return ExtendedIngredient(
            aisle = aisle,
            amount = amount,
            consistency = consistency,
            id = id,
            image = image,
            measure = measure.toMeasure(),
            meta = meta,
            name = name,
            nameClean = nameClean,
            original = original,
            originalName = originalName,
            unit = unit
        )
    }
}