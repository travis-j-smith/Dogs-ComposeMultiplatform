package com.iamtravisjsmith.dogs.doggrid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamtravisjsmith.dogs.DogApi
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogGridViewModel(
    private val dogApi: DogApi,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DogGridUiState(imageUrls = persistentListOf()))
    val uiState: StateFlow<DogGridUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = DogGridUiState(dogApi.fetchDogs().toImmutableList())
        }
    }
}
