package com.example.homework_3.data.remote.dto.recipe_information

data class AnalyzedInstructionDto(
    val name: String,
    val steps: List<StepDto>
)