package com.example.apphuertoencasa.ui.theme.viewModel

import com.example.apphuertoencasa.ui.theme.model.VerdurasModels

sealed interface VerdurasUiState {
    object Loading : VerdurasUiState
    data class Success(val response: VerdurasModels) : VerdurasUiState
    data class Error(val message: String) : VerdurasUiState
}

