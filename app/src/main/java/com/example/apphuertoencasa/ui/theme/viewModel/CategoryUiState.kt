package com.example.apphuertoencasa.ui.theme.viewModel

import com.example.apphuertoencasa.ui.theme.model.CategoryModels

sealed interface CategoryUiState {
    data class Success(val response: CategoryModels) : CategoryUiState
    data class Error(val message: String) : CategoryUiState
    object Loading : CategoryUiState
}