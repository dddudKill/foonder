package com.example.homework_3.domain.models.analyzed_recipe_instructions

data class Equipment(
    val id: Int,
    val image: String,
    val name: String,
    val temperature: Temperature
)