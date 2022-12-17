package com.example.foonder.domain.models

data class Recipe (

    val id : Int,
    val title : String,
    val readyInMinutes : Int,
    val servings : Int,
    val image : String,
    val summary : String,
    val cuisines : List<String>,
    val dishTypes : List<String>,
)