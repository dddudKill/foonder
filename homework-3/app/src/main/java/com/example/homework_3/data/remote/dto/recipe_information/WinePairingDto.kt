package com.example.homework_3.data.remote.dto.recipe_information

data class WinePairingDto(
    val pairedWines: List<Any>,
    val pairingText: String,
    val productMatches: List<Any>
)