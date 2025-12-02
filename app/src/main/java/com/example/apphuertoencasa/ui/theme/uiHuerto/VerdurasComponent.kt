package com.example.apphuertoencasa.ui.theme.uiHuerto

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.apphuertoencasa.R
import com.example.apphuertoencasa.ui.theme.FrutasComponent
import com.example.apphuertoencasa.ui.theme.viewModel.ProductRowContador

//import com.example.apphuertoencasa.ui.theme.viewModel.ProductRowContador

@Composable
fun VerdurasComponent(disminuir : (() -> Unit),
                      aumentar: (() -> Unit),
                      contador: Int,
                      stock: Int = 0,
                      text: String? = "",
                      imageResId: Int){
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
        if (text != null) {
            Text(text)
        }
     ProductRowContador(disminuir, aumentar, contador)
    }
}

@Composable
fun ProductoItem(
    imageResId: Int
) {
    AsyncImage(
        model = imageResId,
        contentDescription = null,
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(40.dp)),
        contentScale = ContentScale.Crop
    )
}

/*@SuppressLint("ViewModelConstructorInComposable")
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
}*/
