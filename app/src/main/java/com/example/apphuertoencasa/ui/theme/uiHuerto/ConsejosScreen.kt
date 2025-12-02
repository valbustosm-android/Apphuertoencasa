package com.example.apphuertoencasa.ui.theme.uiHuerto

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.apphuertoencasa.R
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsejosScreen(navController: NavHostController) {
    val inPreview = LocalInspectionMode.current

    var started by remember { mutableStateOf(inPreview) }
    LaunchedEffect(Unit) { if (!inPreview) started = true }

    val infinite = rememberInfiniteTransition(label = "breathing")
    val pulse by infinite.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val titleAlpha by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(450, easing = FastOutSlowInEasing),
        label = "title-alpha"
    )
    val titleOffset by animateDpAsState(
        targetValue = if (started) 0.dp else (-14).dp,
        animationSpec = tween(450, easing = FastOutSlowInEasing),
        label = "title-offset"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = titleOffset)
                    .alpha(titleAlpha)
                    .graphicsLayer {
                        scaleX = pulse
                        scaleY = pulse
                    }
                    .background(
                        color = Color(0xFFF19737),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(vertical = 16.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Consejos saludables",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text("• Incluir una variedad de alimentos: Trata de tener una dieta equilibrada que incluya frutas, verduras, proteínas (de preferencia magras), carbohidratos integrales y grasas saludables.")
                Text("• Comer porciones adecuadas: No es solo lo que comes, sino también cuánto comes. Las porciones controladas ayudan a mantener un equilibrio calórico adecuado.")
                Text("• Comer frutas y verduras de todos los colores: Cuantos más colores, más nutrientes. Asegúrate de incluir una amplia variedad de frutas y verduras en tus comidas.")
                Text("• Evitar alimentos procesados: Los productos ultraprocesados suelen ser altos en azúcares añadidos, sodio y grasas poco saludables. Intenta comer alimentos frescos siempre que puedas.")
                Text("• Beber suficiente agua: El agua es fundamental para mantenerte hidratado y mejorar la digestión. Intenta evitar bebidas azucaradas.")

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.mujer_banana),
                    contentDescription = "Mujer comiendo banana",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .padding(6.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConsejosScreen() {
    val navController = rememberNavController()
    ConsejosScreen(navController)
}
