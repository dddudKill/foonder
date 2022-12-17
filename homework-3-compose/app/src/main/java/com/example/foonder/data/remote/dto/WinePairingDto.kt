package com.example.foonder.data.remote.dto

data class WinePairingDto(
    val pairedWines: List<Any>,
    val pairingText: String,
    val productMatches: List<Any>
)