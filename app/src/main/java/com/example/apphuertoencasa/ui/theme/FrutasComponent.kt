package com.example.apphuertoencasa.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.apphuertoencasa.ui.theme.viewModel.ProductRowContador

//import com.example.apphuertoencasa.ui.theme.viewModel.ProductRowContador


@Composable
fun FrutasComponent(
    disminuir: () -> Unit,
    aumentar: () -> Unit,
    stock: Int = 0,
    text: String? = "",
    imageResId: String? = ""
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductoItemFrutas(imageResId)
        if (text != null) {
            Text(text)
        }
        ProductRowContador(disminuir, aumentar, stock)
    }
}

@Composable
fun ProductoItemFrutas(
    imageResId: String?
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