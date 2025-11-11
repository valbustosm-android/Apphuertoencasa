package com.example.apphuertoencasa.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apphuertoencasa.R
import com.example.apphuertoencasa.ui.theme.uiHuerto.VerdurasComponent
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel

@Composable
fun LazyVerticalGridVerduras(viewModel: ContadorViewModel){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp)
    ){
        items (1) { fruta ->
            VerdurasComponent({ viewModel.disminuirContadorZanahoria()}, {viewModel.aumentarContadorZanahoria()},contador = viewModel.contadorZanahoria.value,"Zanahorias crujientes cultivadas sin pesticidas en la Región de O'Higgins. Precio: 900 CLP/kg", imageResId = R.drawable.zanahoria)
        }
        items (1) { fruta ->
            VerdurasComponent({viewModel.disminuirContadorEspinaca()}, {viewModel.aumentarContadorEspinaca()},viewModel.contadorEspinaca.value,"Espinacas frescas y nutritivas, perfectas para ensaladas y batidos verdes. Precio: 700 CLP por bolsa de 500", imageResId = R.drawable.espinaca)
        }
        items (1) { fruta ->
            VerdurasComponent({viewModel.disminuirContadorMorron()}, {viewModel.aumentarContadorMorron()},viewModel.contadorMorron.value,"Pimientos, ideales para salteados y platos coloridos. Ricos en antioxidantes y vitaminas Precio:1500 CLP por kilo", imageResId = R.drawable.morron)
        }
        items (1) { fruta ->
            VerdurasComponent({viewModel.disminuirContadorLechuga()}, {viewModel.aumentarContadorLechuga()},viewModel.contadorLechuga.value,"La lechuga orgánica es una hortaliza de hojas verdes, cultivada sin  fertilizantes químicos Precio: 1000 por unidad", imageResId = R.drawable.lechuga)
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun LazyVerticalGridVerduras(){
    LazyVerticalGridVerduras(viewModel = ContadorViewModel())
}


