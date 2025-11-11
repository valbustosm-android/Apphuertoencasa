package com.example.apphuertoencasa.ui.theme.uiHuerto

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel

@Composable
fun Carrito(navCtrl: NavHostController, viewModel: ContadorViewModel) {

    val colorLightOrange = Color(0xFFF4EBD0)
    val colorOrange = Color.White

    var animateBackgroundColor by remember { mutableStateOf(false) }

    val animatedColor by animateColorAsState(
        targetValue = if (animateBackgroundColor)  colorLightOrange else colorOrange,
        animationSpec = tween(durationMillis = 1500),
        label = "color"
    )

    LaunchedEffect(Unit) {
        while(true){
            animateBackgroundColor = !animateBackgroundColor
            kotlinx.coroutines.delay(1800)
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind { drawRect(animatedColor) }
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

        ProductRow(name = "Manzana", pricePerKg = 900, contador = viewModel.contador.value)
        Spacer(modifier = Modifier.padding(top = 20.dp))
        ProductRow(name = "Plátano", pricePerKg = 500, contador = viewModel.contadorPlatano.value)
        ProductRow(name = "Naranja", pricePerKg = 600, contador = viewModel.contadorNaranja.value)
        ProductRow(name = "Melón", pricePerKg = 2000, contador = viewModel.contadorMelon.value)
        ProductRow(name = "Zanahoria", pricePerKg = 900, contador = viewModel.contadorZanahoria.value)
        ProductRow(name = "Espinaca", pricePerKg = 700, contador = viewModel.contadorEspinaca.value)

        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(((viewModel.contador.value * 900) +
                    (viewModel.contadorPlatano.value * 500) +
                    (viewModel.contadorNaranja.value * 600) +
                    (viewModel.contadorMelon.value * 2000) +
                    (viewModel.contadorZanahoria.value * 900) +
                    (viewModel.contadorEspinaca.value * 700)
                    ).toString())

            ButtonPagar(
                text = "Pagar",
                onClick = { navCtrl.navigate(route = "Finalizar Compra") },
                height = 10.dp,
                containerColor = Color(0xFFB3E5FC),
                contentColor = Color.White
            )
        }
    }
}

@Composable
fun ProductRow(
    name: String,
    pricePerKg: Int,
    contador: Int
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(name, fontWeight = FontWeight.Bold)
            Text("$${pricePerKg}")
            Text("$${pricePerKg * contador}")
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(0xFFE0E0E0)
        )
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun CarritoCompra(){
      Carrito(
          navCtrl = rememberNavController(),
          viewModel = ContadorViewModel()
      )
}

