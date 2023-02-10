//package com.example.homework_3.presentation.recipe_detail
//
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.homework_3.common.Constants
//import com.example.homework_3.common.Resource
//import com.example.homework_3.domain.use_case.get_recipe.GetRecipeUseCase
//import kotlinx.coroutines.flow.launchIn
//import kotlinx.coroutines.flow.onEach
//
//
//class RecipeDetailViewModel(
//    private val getRecipeUseCase: GetRecipeUseCase,
//    private val savedStateHandle: SavedStateHandle
//) : ViewModel() {
//
//    private val _state = mutableStateOf(RecipeDetailState())
//    val state: Lifecycle.State<RecipeDetailState> = _state
//
//    init {
//        savedStateHandle.get<Int>(Constants.RECIPE_ID)?.let { id ->
//            getRecipe(id)
//        }
//    }
//
//    private fun getRecipe(number: Int) {
//        getRecipeUseCase(number).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = RecipeDetailState(recipe = result.data)
//                }
//                is Resource.Error -> {
//                    _state.value = RecipeDetailState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//                is Resource.Loading -> {
//                    _state.value = RecipeDetailState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
//}