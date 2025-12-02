package com.example.apphuertoencasa.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import com.example.apphuertoencasa.ui.theme.model.Fruta
import com.example.apphuertoencasa.ui.theme.model.Verdura
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.collections.map

class ContadorViewModel : ViewModel() {
    private val _frutas = MutableStateFlow<List<Fruta>>(emptyList())
    val frutas: StateFlow<List<Fruta>> = _frutas

    private val _verduras = MutableStateFlow<List<Verdura>>(emptyList())
    val verduras: StateFlow<List<Verdura>> = _verduras

    fun setFrutasList(frutas: List<Fruta>) {
        _frutas.value = frutas
    }

    fun setVerdurasList(verduras: List<Verdura>) {
        _verduras.value = verduras
    }


    fun aumentarContador(fruta: Fruta) {
        _frutas.value = frutas.value.map { f ->
            if (fruta.id == f.id) {
                if (f.contador < f.stock) {
                    f.copy(contador = f.contador + 1)
                } else f
            } else f
        }
    }

    fun disminuirContador(fruta: Fruta) {
        _frutas.value = frutas.value.map { f ->
            if (fruta.id == f.id) {
                if (f.contador > 0) {
                    f.copy(contador = f.contador - 1)
                } else f
            } else f
        }
    }

    fun aumentarContadorVerdura(verdura: Verdura) {
        _verduras.value = verduras.value.map { f ->
            if (verdura.id == f.id) {
                if (f.contador < f.stock) {
                    f.copy(contador = f.contador + 1)
                } else f
            } else f
        }
    }


    fun disminuirContadorVerdura(verdura: Verdura) {
        _verduras.value = verduras.value.map { f ->
            if (verdura.id == f.id) {
                if (f.contador > 0) {
                    f.copy(contador = f.contador - 1)
                } else f
            } else f
        }
    }
}