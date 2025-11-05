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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Carrito(navCtrl: NavHostController) {
    val scrollState = rememberScrollState()
    var qty by remember { mutableStateOf(1) }

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
                "Carrito",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))

        ProductRow(
            name = "Manzana",
            pricePerKg = 600,
            quantityKg = qty,
            onMinus = { if(qty > 1) qty-- },
            onPlus = { qty++ }
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        ProductRow(
            name = "Plátano",
            pricePerKg = 500,
            quantityKg = qty,
            onMinus = { if(qty > 1) qty-- },
            onPlus = { qty++ }
        )

        ProductRow(
            name = "Naranja",
            pricePerKg = 600,
            quantityKg = qty,
            onMinus = { if(qty > 1) qty-- },
            onPlus = { qty++ }
        )
        ProductRow(
            name = "Melón",
            pricePerKg = 2000,
            quantityKg = qty,
            onMinus = { if(qty > 1) qty-- },
            onPlus = { qty++ }
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Total a pagar: $30.000")
            ButtonPagar(
                text = "Pagar",
                onClick = { navCtrl.navigate(route = "Confirmar pedido")},
                height = 10.dp,
                containerColor = Color(0xFFF19737),
                contentColor = Color.White
            )
        }
    }
}


@Composable
fun ProductRow(
    name: String,
    pricePerKg: Int,
    quantityKg: Int,
    onMinus: () -> Unit,
    onPlus: () -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(name)
            Text("$${pricePerKg}")

            Icon(
                imageVector = Icons.Filled.RemoveCircleOutline,
                contentDescription = "Remove",
                modifier = Modifier.padding(start = 20.dp),
                tint = Color(0xFF4CAF50)
            )

            Text("${quantityKg} kg")

            Icon(
                imageVector = Icons.Filled.AddCircleOutline,
                contentDescription = "Add",
                tint = Color(0xFF4CAF50)
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(0xFFE0E0E0)
        )

    }
}
