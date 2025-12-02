package com.example.apphuertoencasa.ui.theme.uiHuerto

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.apphuertoencasa.ui.theme.FrutasComponent
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel


@Composable
fun LazyVerticalGridFruta(
    viewModel: ContadorViewModel,
) {
    val frutas by viewModel.frutas.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp)
    ) {
        frutas.forEach { fruta ->
            items(1) {
                FrutasComponent(
                    { viewModel.disminuirContador(fruta) },
                    { viewModel.aumentarContador(fruta) },
                    text = fruta.description,
                    stock = fruta.contador,
                    imageResId = fruta.imageUrl
                )
            }

        }
    }
}
