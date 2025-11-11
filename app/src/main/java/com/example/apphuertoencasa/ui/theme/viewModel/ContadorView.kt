package com.example.apphuertoencasa.ui.theme.viewModel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProductRowContador(disminuir : () -> Unit, aumentar: () -> Unit, contador: Int) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.RemoveCircleOutline,
                contentDescription = "Remove",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable {
                        disminuir.invoke()
                    },
                tint = Color(0xFF4CAF50),
            )
            Text(contador.toString())
            Icon(
                imageVector = Icons.Filled.AddCircleOutline,
                contentDescription = "Add",
                tint = Color(0xFF4CAF50),
                modifier = Modifier.clickable {
                  aumentar.invoke()
                }
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(0xFFE0E0E0)
        )

    }
}