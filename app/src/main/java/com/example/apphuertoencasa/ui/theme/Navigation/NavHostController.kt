package com.example.apphuertoencasa.ui.theme.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apphuertoencasa.ui.theme.uiHuerto.Carrito
import com.example.apphuertoencasa.ui.theme.uiHuerto.Categoria
import com.example.apphuertoencasa.ui.theme.uiHuerto.ConfirmarPedido
import com.example.apphuertoencasa.ui.theme.uiHuerto.FrutasFrescas
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login
import com.example.apphuertoencasa.ui.theme.uiHuerto.SignUp
import com.example.apphuertoencasa.ui.theme.uiHuerto.VerdurasOrganicas

@Composable
fun NavHostController(modifier: Modifier){
    val navCtrl = rememberNavController()

    NavHost(navController = navCtrl,
            startDestination = "Login"){
        composable(route="Login"){ Login(navCtrl, modifier = Modifier
          /*  onSuccessfullLong = {
                navCtrl.navigate("Categoria")
            }*/) }
        composable (route= "Sign In") { SignUp(modifier = Modifier, navCtrl,
            onSuccessfullRegister = {
                navCtrl.navigate("Categoria")
            }) }
        composable (route = "Categoria"){Categoria(navCtrl)}
        composable (route = "Verduras organicas") { VerdurasOrganicas() }
        composable  (route = "Frutas frescas") { FrutasFrescas(navCtrl) }
        composable  (route = "Carrito") { Carrito(navCtrl) }
        composable  (route = "Confirmar pedido") { ConfirmarPedido() }
    }
}