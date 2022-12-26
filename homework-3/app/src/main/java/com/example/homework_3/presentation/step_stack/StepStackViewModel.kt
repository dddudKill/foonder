package com.example.homework_3.presentation.step_stack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_3.ServiceLocator
import com.example.homework_3.common.Resource
import com.example.homework_3.domain.models.analyzed_recipe_instructions.RecipeInstruction
import kotlinx.coroutines.flow.*

class StepStackViewModel : ViewModel() {

    private val repository = ServiceLocator.repository()

    private val _state = MutableStateFlow(StepStackListState())
    val state: StateFlow<StepStackListState> = _state

    fun getInstructions(id: Int) {
        repository.getRecipeAnalyzedInstructions(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        instruction = result.data ?: RecipeInstruction(name = "None found", steps = emptyList()),
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