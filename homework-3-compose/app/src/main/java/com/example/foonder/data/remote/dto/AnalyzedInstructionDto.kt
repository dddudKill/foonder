package com.example.foonder.data.remote.dto

import com.example.foonder.domain.models.AnalyzedInstruction

data class AnalyzedInstructionDto(
    val name: String,
    val steps: List<StepDto>
) {
    fun toAnalyzedInstruction(): AnalyzedInstruction {
        return AnalyzedInstruction(
            name = name,
            steps = steps.map { it.toStep() }
        )
    }
}