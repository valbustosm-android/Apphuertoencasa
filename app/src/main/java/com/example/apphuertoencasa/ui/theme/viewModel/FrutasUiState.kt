package com.example.apphuertoencasa.ui.theme.viewModel

import com.example.apphuertoencasa.ui.theme.model.Fruta
import com.example.apphuertoencasa.ui.theme.model.FrutasModels

sealed interface FrutasUiState {
    object Loading : FrutasUiState
    data class Success(val response: FrutasModels) : FrutasUiState
    data class Error(val message: String) : FrutasUiState
}
