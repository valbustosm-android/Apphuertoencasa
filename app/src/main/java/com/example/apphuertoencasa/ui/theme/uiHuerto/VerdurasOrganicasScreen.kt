package com.example.apphuertoencasa.ui.theme.uiHuerto

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apphuertoencasa.ui.theme.LazyVerticalGridVerduras
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel

@Composable
fun VerdurasOrganicasScreen(
    viewModel: ContadorViewModel,
    onClick: (() -> Unit)
) {
    var started by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { started = true }

    // Pulso infinito (breathing)
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
            Spacer(modifier = Modifier.height(50.dp))
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
                    "Verduras Orgánicas",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            LazyVerticalGridVerduras(viewModel)
        }
        AnimatedVisibility(
            visible = started,
            enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it / 2 }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            FloatingActionButton(
                onClick = { onClick.invoke() },
                containerColor = Color(0xFFF19737),
                contentColor = Color.White,
                modifier = Modifier
                    .width(160.dp)
                    .height(60.dp)
                    .graphicsLayer {
                        scaleX = pulse
                        scaleY = pulse
                    }
            ) {
                Text("Pagar")
            }
        }
    }
}
