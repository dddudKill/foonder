package com.example.foonder.presentation.recipe_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foonder.common.Constants
import com.example.foonder.common.Resource
import com.example.foonder.domain.use_case.get_recipe.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(RecipeDetailState())
    val state: State<RecipeDetailState> = _state

    init {
        savedStateHandle.get<Int>(Constants.RECIPE_ID)?.let { id ->
            getRecipe(id)
        }
    }

    private fun getRecipe(number: Int) {
        getRecipeUseCase(number).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RecipeDetailState(recipe = result.data)
                }
                is Resource.Error -> {
                    _state.value = RecipeDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = RecipeDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}