package com.example.homework_3.presentation.recipe_stack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_3.ServiceLocator
import com.example.homework_3.common.Resource
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient
import kotlinx.coroutines.flow.*

class RecipeStackViewModel : ViewModel() {

    private val repository = ServiceLocator.repository()

    private val _state = MutableStateFlow(RecipeStackListState())
    val state: StateFlow<RecipeStackListState> = _state

    fun getRecipes(ingredients: List<ExtendedIngredient>, number: Int? = null) {
        repository.getRecipesByIngredients(ingredients, number).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        recipes = result.data ?: emptyList(),
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