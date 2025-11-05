package com.example.apphuertoencasa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.apphuertoencasa.ui.theme.ApphuertoencasaTheme
import com.example.apphuertoencasa.ui.theme.Navigation.NavHostController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApphuertoencasaTheme {
                val navCtrl = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                /*  Login(
                        modifier = Modifier.padding(innerPadding),
                        navCtrl = navCtrl
                    )*/
                   /* SignUp(
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    NavHostController(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

