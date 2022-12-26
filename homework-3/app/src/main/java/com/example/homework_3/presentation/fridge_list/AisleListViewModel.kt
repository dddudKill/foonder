package com.example.homework_3.presentation.fridge_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_3.ServiceLocator
import com.example.homework_3.common.Resource
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient
import kotlinx.coroutines.flow.*

class AisleListViewModel : ViewModel() {

    private val repository = ServiceLocator.repository()

    private val _state = MutableStateFlow(AisleListState())
    val state: StateFlow<AisleListState> = _state

    init {
        getAisles()
    }

    fun getAisles() {
        repository.getAisles().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        aisles = result.data ?: emptyMap<String, List<ExtendedIngredient>>(),
                        isLoading = false,
                        error = ""
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}