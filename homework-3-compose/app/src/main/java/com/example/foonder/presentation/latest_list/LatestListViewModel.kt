package com.example.foonder.presentation.latest_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foonder.common.Constants
import com.example.foonder.common.Resource
import com.example.foonder.domain.use_case.get_recipes.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LatestListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(LatestListState())
    val state: State<LatestListState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        val tags = savedStateHandle.get<String>(Constants.TAGS) ?: ""
        savedStateHandle.get<Int>("number")?.let {
            //getLatest(it, tags)
        }
        getLatest(4, null)
    }

    private fun getLatest(number: Int, tags: String?) {
        getRecipesUseCase(number, tags).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        latest = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(UIEvent.ShowSnackbar(
                        result.message ?: "An unexpected error occured"
                    ))
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
    }
}