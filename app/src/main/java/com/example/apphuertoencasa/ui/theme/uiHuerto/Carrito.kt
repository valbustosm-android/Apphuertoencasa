package com.example.apphuertoencasa.ui.theme.uiHuerto

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carrito(
    navCtrl: NavHostController,
    viewModel: ContadorViewModel
) {
    val bgColor = Color(0xFFFFF6E5)
    val orange = Color(0xFFF19737)
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .verticalScroll(scroll)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = bgColor
            ),
            title = {},
            navigationIcon = {
                IconButton(onClick = { navCtrl.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.Black
                    )
                }
            }
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = orange)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Carrito",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text("Producto", fontWeight = FontWeight.Bold)
            Text("Cantidad", fontWeight = FontWeight.Bold)
            Text("Precio", fontWeight = FontWeight.Bold)
            Text("Total", fontWeight = FontWeight.Bold)
        }
        viewModel.frutas.collectAsState().value.forEach { fruta ->
            if (fruta.contador > 0)
                ProductRowCard(fruta.name, fruta.precio, fruta.contador)
        }

        viewModel.verduras.collectAsState().value.forEach { verdura ->
            if (verdura.contador > 0)
                ProductRowCard(verdura.name, verdura.precio, verdura.contador)
        }

        Spacer(Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                val sumaTotal = viewModel.frutas.collectAsState().value.sumOf { it.contador * it.precio } + viewModel.verduras.collectAsState().value.sumOf { it.contador * it.precio }
                Text(
                    "$${sumaTotal}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = orange
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { navCtrl.navigate("Finalizar Compra") },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4FC3F7),
                contentColor = Color.White
            )
        ) {
            Text("Pagar ahora", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(30.dp))
    }
}

@Composable
fun ProductRowCard(name: String?, pricePerKg: Int, contador: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (name != null) {
                Text(name, fontWeight = FontWeight.Bold)
            }

            Text(contador.toString(), fontWeight = FontWeight.Bold)

            Text("$${pricePerKg}", color = Color(0xFF6D6D6D))
            Text(
                "$${pricePerKg * contador}",
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF19737)
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun CarritoCompra() {
    Carrito(
        navCtrl = rememberNavController(),
        viewModel = ContadorViewModel()
    )
}


