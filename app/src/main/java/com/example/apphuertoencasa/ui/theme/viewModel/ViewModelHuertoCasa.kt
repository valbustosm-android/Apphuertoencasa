package com.example.apphuertoencasa.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apphuertoencasa.Network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class CategoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)

    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    init {
        fetchCategories()
    }
    fun fetchCategories() {
        _uiState.value = CategoryUiState.Loading
        viewModelScope.launch {
            try {
                val categoryResponse = RetrofitInstance.api.getCategories()
                _uiState.value = CategoryUiState.Success(categoryResponse)
            } catch (e: IOException) {
                _uiState.value = CategoryUiState.Error("Error de red: ${e.message}")
            } catch (e: Exception) {
                _uiState.value = CategoryUiState.Error("Error desconocido: ${e.message}")
            }
        }
    }
}