package com.example.homework_2.ui.main

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.homework_2.ServiceLocator

 class MainViewModel : ViewModel() {
    private val provider = ServiceLocator.provider()

    fun getGifs() = provider.getGifs()
        .cachedIn(viewModelScope)

     suspend fun getGif(id: String) = provider.getGif(id)
 }