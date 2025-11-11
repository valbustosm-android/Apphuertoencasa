package com.example.apphuertoencasa.ui.theme.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ContadorViewModel: ViewModel() {
    //Frutas
    private val _contadorManzana = mutableStateOf(0)
    val contador: State<Int> = _contadorManzana
    private val _contadorPera = mutableStateOf(0)
    val contadorPera: State<Int> = _contadorPera
    private val _contadorPlatano = mutableStateOf(0)
    val contadorPlatano: State<Int> = _contadorPlatano
    private val _contadorNaranja = mutableStateOf(0)
    val contadorNaranja: State<Int> = _contadorNaranja
    private val _contadorKiwi = mutableStateOf(0)
    val contadorKiwi: State<Int> = _contadorKiwi
    private val _contadorMelon = mutableStateOf(0)
    val contadorMelon: State<Int> = _contadorMelon

    //Verduras

    private val _contadorZanahoria = mutableStateOf(0)
    val contadorZanahoria: State<Int> = _contadorZanahoria

    private val _contadorEspinaca = mutableStateOf(0)
    val contadorEspinaca: State<Int> = _contadorEspinaca

    private val _contadorMorron = mutableStateOf(0)
    val contadorMorron: State<Int> = _contadorMorron

    private val _contadorLechuga = mutableStateOf(0)
    val contadorLechuga: State<Int> = _contadorLechuga


    fun aumentarContadorManzana(){
        _contadorManzana.value = _contadorManzana.value + 1
    }
    fun disminuirContadorManzana(){
        _contadorManzana.value = _contadorManzana.value - 1
        if(_contadorManzana.value < 0){
            _contadorManzana.value = 0
        }
    }
    //Pera
    fun aumentarContadorPera(){
        _contadorPera.value = _contadorPera.value + 1
    }
    fun disminuirContadorPera(){
        _contadorPera.value = _contadorPera.value - 1
        if(_contadorManzana.value < 0){
            _contadorManzana.value = 0
        }
    }

    //Plátano
    fun aumentarContadorPlatano(){
        _contadorPlatano.value = _contadorPlatano.value + 1
    }
    fun disminuirContadorPlatano(){
        _contadorPlatano.value = _contadorPlatano.value - 1
        if(_contadorPlatano.value < 0){
            _contadorPlatano.value = 0
        }
    }
    //Naranja
    fun aumentarContadorNaraja(){
        _contadorNaranja.value = _contadorNaranja.value + 1
    }
    fun disminuirContadorNaranja(){
        _contadorNaranja.value = _contadorNaranja.value - 1
        if(_contadorNaranja.value < 0){
            _contadorNaranja.value = 0
        }
    }
    //Kiwi
    fun aumentarContadorKiwi(){
        _contadorKiwi.value = _contadorKiwi.value + 1
    }
    fun disminuirContadorKiwi(){
        _contadorKiwi.value = _contadorKiwi.value - 1
        if(_contadorKiwi.value < 0){
            _contadorKiwi.value = 0
        }
    }
    //Melón
    fun aumentarContadorMelon(){
        _contadorMelon.value = _contadorMelon.value + 1
    }
    fun disminuirContadorMelon(){
        _contadorMelon.value = _contadorMelon.value - 1
        if(_contadorMelon.value < 0){
            _contadorMelon.value = 0
        }
    }

    //VERDURAS
    fun aumentarContadorZanahoria(){
        _contadorZanahoria.value = _contadorZanahoria.value + 1
    }
    fun disminuirContadorZanahoria(){
        _contadorZanahoria.value = _contadorZanahoria.value - 1
        if(_contadorZanahoria.value < 0){
            _contadorZanahoria.value = 0
        }
    }
  //Espinaca
    fun aumentarContadorEspinaca(){
        _contadorEspinaca.value = _contadorEspinaca.value + 1
    }
    fun disminuirContadorEspinaca(){
        _contadorEspinaca.value = _contadorEspinaca.value - 1
        if(_contadorEspinaca.value < 0){
            _contadorEspinaca.value = 0
        }
    }

    //Morron
    fun aumentarContadorMorron(){
        _contadorMorron.value = _contadorMorron.value + 1
    }
    fun disminuirContadorMorron(){
        _contadorMorron.value = _contadorMorron.value - 1
        if(_contadorMorron.value < 0){
            _contadorMorron.value = 0
        }
    }

    //Lechuga
    fun aumentarContadorLechuga(){
        _contadorMorron.value = _contadorMorron.value + 1
    }
    fun disminuirContadorLechuga(){
        _contadorLechuga.value = _contadorLechuga.value - 1
        if(_contadorLechuga.value < 0){
            _contadorLechuga.value = 0
        }
    }

}