package com.example.apphuertoencasa.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apphuertoencasa.Network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FrutasViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<FrutasUiState>(FrutasUiState.Loading)
    val uiState: StateFlow<FrutasUiState> = _uiState

    init {
        getFrutas()
    }
    private fun getFrutas() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getFruits()
                _uiState.value = FrutasUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = FrutasUiState.Error(e.message ?: "Error al cargar frutas")
            }
        }
    }
}