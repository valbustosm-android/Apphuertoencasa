package com.example.apphuertoencasa.ui.theme.uiHuerto

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apphuertoencasa.R
import com.example.apphuertoencasa.ui.theme.FrutasComponent
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel

@Composable
fun LazyVerticalGridFruta(
    viewModel: ContadorViewModel
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp)
    ){
        items (1) { fruta ->
            FrutasComponent({ viewModel.disminuirContadorManzana()}, {viewModel.aumentarContadorManzana()},contador = viewModel.contador.value,"Manzanas Fuji crujientes y dulces, cultivadas en el Valle del Maule.Perfectas para meriendas.  Precio: 900 CLP Kilo", imageResId = R.drawable.manzana)
        }
        items (1) { fruta ->
            FrutasComponent({viewModel.disminuirContadorNaranja()}, {viewModel.aumentarContadorNaraja()},viewModel.contadorNaranja.value,"Naranja Valencia Descripción: Jugosas y ricas en vitamina C, estas naranjas Valencia son ideales para zumos frescos y refrescantes.Precio: 700 CLP kilo", imageResId = R.drawable.naranja )
        }
        items (1) { fruta ->
            FrutasComponent({viewModel.disminuirContadorPlatano()}, {viewModel.aumentarContadorPlatano()},viewModel.contadorPlatano.value,"Plátanos maduros y dulces, perfectos para el desayuno o como snack energético. Estos plátanos son ricos en potasio y vitaminas. Precio: 800 CLP kilo", imageResId = R.drawable.platano)
        }
        items (1) { fruta ->
            FrutasComponent({viewModel.disminuirContadorPera()}, {viewModel.aumentarContadorPera()},viewModel.contadorPera.value,"Pera Fruta jugosa, de sabor suave y ligeramente dulce, con una textura firme pero tierna.Precio: 600 tCLP por kilo", imageResId = R.drawable.pera)
        }
        items (1) { fruta ->
            FrutasComponent({viewModel.disminuirContadorKiwi()}, {viewModel.aumentarContadorKiwi()},viewModel.contadorKiwi.value,"El kiwi es una fruta de forma ovalada, cubierta por una fina piel marrón con una ligera pelusa.Precio:$800 CLP kilo", imageResId = R.drawable.melon)
        }
        items (1) { fruta ->
            FrutasComponent({viewModel.disminuirContadorMelon()}, {viewModel.aumentarContadorMelon()},viewModel.contadorMelon.value,"El melón es una fruta grande, de forma redonda u ovalada, con una cáscara gruesa que puede ser lisa o reticulada según la variedad.Precio: 2000 por unidad",  imageResId = R.drawable.kiwi)
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun LazyVerticalGridFruta(){
    LazyVerticalGridFruta(viewModel = ContadorViewModel())
}
