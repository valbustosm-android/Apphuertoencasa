package com.example.apphuertoencasa.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apphuertoencasa.R
import com.example.apphuertoencasa.ui.theme.uiHuerto.VerdurasComponent
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel

@Composable
fun LazyVerticalGridVerduras(viewModel: ContadorViewModel){

    val verduras by viewModel.verduras.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp)
    ){
        verduras.forEach { verdura ->
            items(1) {
                FrutasComponent(
                    { viewModel.disminuirContadorVerdura(verdura) },
                    { viewModel.aumentarContadorVerdura(verdura) },
                    text = verdura.description,
                    stock = verdura.contador,
                    imageResId = verdura.imageUrl
                )
            }

        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun LazyVerticalGridVerduras(){
    LazyVerticalGridVerduras(viewModel = ContadorViewModel())
}


