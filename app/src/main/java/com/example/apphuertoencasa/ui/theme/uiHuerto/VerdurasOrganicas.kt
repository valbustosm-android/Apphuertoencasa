package com.example.apphuertoencasa.ui.theme.uiHuerto


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apphuertoencasa.R

@Composable
fun VerdurasOrganicas() {
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
                "Verduras Orgánicas",
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
                R.drawable.zanahoria,
            )
            ProductoItem(
                R.drawable.espinaca
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp) // visible al hacer scroll
        ) {
            Text(
                "Zanahorias crujientes cultivadas sin pesticidas en la Región de O'Higgins. Precio: 900 CLP/kg",
                modifier = Modifier.weight(1f)
            )
            Text(
                "Espinacas frescas y nutritivas, perfectas para ensaladas y batidos verdes. Precio: 700 CLP por bolsa de 500",
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
        VerdurasOrganicastwo()
    }
}

@Composable
fun VerdurasOrganicastwo(){
        Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductoItem(
                R.drawable.morron,
            )
            ProductoItem(
                R.drawable.lechuga
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp) // visible al hacer scroll
        ) {
            Text(
                "Pimientos, ideales para salteados y platos coloridos. Ricos en antioxidantes y vitaminas Precio:1500 CLP por kilo",
                modifier = Modifier.weight(1f)
            )
            Text(
                "La lechuga orgánica es una hortaliza de hojas verdes, cultivada sin  fertilizantes químicos Precio: 1000 por unidad",
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
        Spacer(modifier = Modifier.height(8.dp))
}
