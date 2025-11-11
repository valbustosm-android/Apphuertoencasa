package com.example.apphuertoencasa.ui.theme.uiHuerto

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apphuertoencasa.R

@Composable
fun Categoria(navCtrl: NavHostController){
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()
        .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Productos", modifier = Modifier.padding(start = 10.dp, top = 70.dp),
            fontSize = 30.sp, fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 30.dp, start = 30.dp, end = 30.dp).background(
                    color = Color(0xFFF19737),
                    shape = RoundedCornerShape(6.dp)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("ITEMS")
            Text("CATEGORÍAS")
            Text("BLOG")
        }
        Spacer(modifier = Modifier.height(50.dp))
        InfoRowCategoria(
            "Frutas frescas",
            imageRes = R.drawable.frutasfrescas,
            iconRes = R.drawable.flechaderecha,
            description = "Nuestra selección de frutas frescas ofrece una experiencia directa del campo a tu hogar",
            onIconClick = {navCtrl.navigate(route = "Frutas frescas")}
        )

        InfoRowCategoria(
            "Verduras orgánicas",
            imageRes = R.drawable.vedurasorganicas,
            iconRes = R.drawable.flechaderecha,
            description = "Descubre nuestra gama de verduras orgánicas, cultivadas sin el uso de pesticidas ni químicos.",
            onIconClick = {navCtrl.navigate(route = "Verduras orgánicas")}
        )
        InfoRowCategoria(
            "Productos orgánicos",
            imageRes = R.drawable.productoorganico,
            iconRes = R.drawable.flechaderecha,
            description = " Nuestros productos orgánicos están elaborados con ingredientes naturales y procesados de manera responsable.",
            onIconClick = {}
        )
    }
}


@Composable
fun InfoRowCategoria(
    title: String,
    description: String,
    imageRes: Int,
    iconRes: Int,
    onIconClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                    append("$title: ")
                }
                append(description)
            },
            modifier = Modifier
                .weight(1f)
                .padding(start = 30.dp)
        )

        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Image(
            painter = painterResource(iconRes),
            contentDescription = "Icono clickeable",
            modifier = Modifier
                .size(30.dp)
                .clickable { onIconClick() }
        )
    }
}
