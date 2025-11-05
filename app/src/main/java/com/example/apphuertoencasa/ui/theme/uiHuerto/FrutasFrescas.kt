package com.example.apphuertoencasa.ui.theme.uiHuerto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apphuertoencasa.R

@Composable
fun FrutasFrescas(navCtrl: NavHostController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF19737),
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(vertical = 16.dp, horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Frutas Frescas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 30.dp)
        ) {
            ProductoItem(
                R.drawable.manzana,
            )
            ProductoItem(
                R.drawable.naranja
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp) // visible al hacer scroll
        ) {
            Text(
                " Manzanas Fuji crujientes y dulces, cultivadas en el Valle del Maule.Perfectas para meriendas. Precio: 900 CLP Kilo",
                modifier = Modifier.weight(1f)
            )
            Text(
                "Naranja Valencia Descripción: Jugosas y ricas en vitamina C, estas naranjas Valencia son ideales para zumos frescos y refrescantes.Precio: 700 CLP kilo",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppSocialMediaButton(
                text = "Agregar",
                onClick = {navCtrl.navigate(route = "Carrito")},
                height = 30.dp,
                containerColor = Color(0xFF629C44),
                contentColor = Color.White
            )
            AppSocialMediaButton(
                text = "Agregar",
                onClick = { /* ... */ },
                height = 30.dp,
                containerColor = Color(0xFF629C44),
                contentColor = Color.White
            )
        }
        FrutasFrescasTwo()
    }
}

@Composable
fun FrutasFrescasTwo(){
    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProductoItem(
            R.drawable.platano,
        )
        ProductoItem(
            R.drawable.pera
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 20.dp) // visible al hacer scroll
    ) {
        Text(
            "Plátanos maduros y dulces, perfectos para el desayuno o como snack energético. Estos plátanos son ricos en potasio y vitaminas. Precio: 800 CLP kilo",
            modifier = Modifier.weight(1f)
        )
        Text(
            "Pera\n" +
                    "Fruta jugosa, de sabor suave y ligeramente dulce, con una textura firme pero tierna.\n" +
                    "\n" +
                    "Precio: \$600 \tCLP por kilo",
            modifier = Modifier.weight(1f)
        )
    }
    Spacer(Modifier.height(24.dp)) // margen final
    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppSocialMediaButton(
            text = "Agregar",
            onClick = { /* ... */ },
            height = 30.dp,
            containerColor = Color(0xFF629C44),
            contentColor = Color.White
        )
        AppSocialMediaButton(
            text = "Agregar",
            onClick = { /* ... */ },
            height = 30.dp,
            containerColor = Color(0xFF629C44),
            contentColor = Color.White
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProductoItem(
            R.drawable.melon,
        )
        ProductoItem(
            R.drawable.kiwi
        )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 20.dp) // visible al hacer scroll
    ) {
        Text(
            "El kiwi es una fruta de forma ovalada, cubierta por una fina piel marrón con una ligera pelusa\n" +
                    "\n" +
                    "Precio: \$800 CLP kilo\n" +
                    "\n",
            modifier = Modifier.weight(1f)
        )
        Text(
            "El melón es una fruta grande, de forma redonda u ovalada, con una cáscara gruesa que puede ser lisa o reticulada según la variedad.\n" +
                    "\n" +
                    "Precio: \$2000 por unidad",
            modifier = Modifier.weight(1f)
        )
    }
    Spacer(Modifier.height(24.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppSocialMediaButton(
            text = "Agregar",
            onClick = { /* ... */ },
            height = 30.dp,
            containerColor = Color(0xFF629C44),
            contentColor = Color.White
        )
        AppSocialMediaButton(
            text = "Agregar",
            onClick = { /* ... */ },
            height = 30.dp,
            containerColor = Color(0xFF629C44),
            contentColor = Color.White
        )
    }
}

