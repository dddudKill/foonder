package com.example.foonder.domain.models

import com.example.foonder.domain.models.Equipment
import com.example.foonder.domain.models.Ingredient
import com.example.foonder.domain.models.Length

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val length: Length,
    val number: Int,
    val step: String
)
