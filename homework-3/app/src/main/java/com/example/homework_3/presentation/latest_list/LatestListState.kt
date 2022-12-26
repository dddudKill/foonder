package com.example.homework_3.presentation.latest_list

import com.example.homework_3.domain.models.recipe_information.Recipe


data class LatestListState(
    val isLoading: Boolean = false,
    val latest: List<Recipe> = emptyList(),
    val error: String = ""
)
