package com.example.apphuertoencasa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.apphuertoencasa.ui.theme.ApphuertoencasaTheme
import com.example.apphuertoencasa.ui.theme.Navigation.NavHostController
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel

class MainActivity : ComponentActivity() {
    val viewModel: ContadorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApphuertoencasaTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHostController(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

