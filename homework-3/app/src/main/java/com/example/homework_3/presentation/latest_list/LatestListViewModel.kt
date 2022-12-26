package com.example.homework_3.presentation.latest_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_3.ServiceLocator
import com.example.homework_3.common.Resource
import kotlinx.coroutines.flow.*

class LatestListViewModel : ViewModel() {

    private val repository = ServiceLocator.repository()

    private val _state = MutableStateFlow(LatestListState())
    val state: StateFlow<LatestListState> = _state


    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getLatest(number: Int, tags: String?) {
        repository.getRecipes(number, tags).onEach { result ->
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