package com.example.apphuertoencasa.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apphuertoencasa.Network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VerdurasViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<VerdurasUiState>(VerdurasUiState.Loading)
    val uiState: StateFlow<VerdurasUiState> = _uiState

    init {
        getVerduras()
    }
    private fun getVerduras() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getVegetables()
                _uiState.value = VerdurasUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = VerdurasUiState.Error(e.message ?: "Error al cargar frutas")
            }
        }
    }
}