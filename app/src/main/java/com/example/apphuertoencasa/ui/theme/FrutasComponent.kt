package com.example.apphuertoencasa.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apphuertoencasa.R
import com.example.apphuertoencasa.ui.theme.viewModel.ProductRowContador


@Composable
fun FrutasComponent(disminuir : (() -> Unit), aumentar: (() -> Unit), contador: Int, text: String, imageResId: Int){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp).padding(top = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductoItem(
          imageResId
             )
        Text(text)
        ProductRowContador(disminuir, aumentar, contador)
    }
}

@Composable
fun ProductoItem(
    imageResId: Int
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = Modifier.size(150.dp)
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
 fun PreviewFrutasScreen(){
    var count by rememberSaveable { mutableStateOf(0) }

    FrutasComponent(
        disminuir = { if (count > 0) count-- },
        aumentar = { count++ },
        contador = count,
        text = "Manzanas Fuji crujientes y dulces",
        imageResId = R.drawable.manzana
    )
}