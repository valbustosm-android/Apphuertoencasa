package com.example.apphuertoencasa.ui.theme.uiHuerto

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apphuertoencasa.R
import com.example.apphuertoencasa.ui.theme.data.MyPrefs
import com.example.apphuertoencasa.ui.theme.model.Purchase
import com.example.apphuertoencasa.ui.theme.model.PurchaseItem
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalizarCompra(viewModel: ContadorViewModel, navCtrl: NavHostController) {
    val context = LocalContext.current
    val prefs = remember { MyPrefs(context) }
    val frutas = viewModel.frutas.collectAsState().value
    val verduras = viewModel.verduras.collectAsState().value
    
    LaunchedEffect(Unit) {
        // Obtener productos con contador > 0
        val purchaseItems = mutableListOf<PurchaseItem>()
        var totalPrice = 0
        
        frutas.forEach { fruta ->
            if (fruta.contador > 0) {
                val itemTotal = fruta.contador * fruta.precio
                purchaseItems.add(
                    PurchaseItem(
                        name = fruta.name ?: "Producto sin nombre",
                        quantity = fruta.contador,
                        pricePerUnit = fruta.precio,
                        total = itemTotal
                    )
                )
                totalPrice += itemTotal
            }
        }
        
        verduras.forEach { verdura ->
            if (verdura.contador > 0) {
                val itemTotal = verdura.contador * verdura.precio
                purchaseItems.add(
                    PurchaseItem(
                        name = verdura.name ?: "Producto sin nombre",
                        quantity = verdura.contador,
                        pricePerUnit = verdura.precio,
                        total = itemTotal
                    )
                )
                totalPrice += itemTotal
            }
        }
        
        // Guardar la compra solo si hay productos
        if (purchaseItems.isNotEmpty()) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val purchase = Purchase(
                id = UUID.randomUUID().toString(),
                date = dateFormat.format(Date()),
                items = purchaseItems,
                totalPrice = totalPrice
            )
            prefs.savePurchase(purchase)
        }
    }
    val scale by rememberInfiniteTransition().animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFE0B2),
                        Color(0xFFFFCC80)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            val bgColor = Color(0xFFFFF6E5)
            val orange = Color(0xFFF19737)
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
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.compra),
                    contentDescription = "Icono compra finalizada",
                    modifier = Modifier.size(180.dp)
                )

                Spacer(Modifier.height(20.dp))
                Text(
                    "¡Compra Completada!",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50)
                )

                Spacer(Modifier.height(8.dp))
                Text(
                    "Gracias por preferirnos ♥",
                    fontSize = 18.sp,
                    color = Color(0xFF616161),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.scale(scale)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFinalizarCompra() {
    // Preview sin ViewModel
}


