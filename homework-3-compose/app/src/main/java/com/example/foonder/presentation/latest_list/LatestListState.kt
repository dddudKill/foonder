package com.example.foonder.presentation.latest_list

import com.example.foonder.domain.models.Recipe

data class LatestListState(
    val isLoading: Boolean = false,
    val latest: List<Recipe> = emptyList(),
    val error: String = ""
)
