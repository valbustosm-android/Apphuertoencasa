package com.example.apphuertoencasa.ui.theme.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apphuertoencasa.ui.theme.FrutasComponent
import com.example.apphuertoencasa.ui.theme.FrutasFrescasScreen
import com.example.apphuertoencasa.ui.theme.uiHuerto.Carrito
import com.example.apphuertoencasa.ui.theme.uiHuerto.Categoria
import com.example.apphuertoencasa.ui.theme.uiHuerto.ConfirmarPedido
import com.example.apphuertoencasa.ui.theme.uiHuerto.FinalizarCompra
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.Login
import com.example.apphuertoencasa.ui.theme.uiHuerto.Signup.SignUp
import com.example.apphuertoencasa.ui.theme.uiHuerto.VerdurasOrganicasScreen
import com.example.apphuertoencasa.ui.theme.viewModel.ContadorViewModel

@Composable
fun NavHostController(modifier: Modifier, viewModel: ContadorViewModel){

    val navCtrl = rememberNavController()

    NavHost(navController = navCtrl,
            startDestination = "Login"){
        composable(route="Login"){ Login(navCtrl, modifier = Modifier
          /*  onSuccessfullLong = {
                navCtrl.navigate("Categoria")
            }*/) }
        composable (route= "Sign In") { SignUp(modifier = Modifier, navCtrl,
            onSuccessfullRegister = {
                navCtrl.navigate("Login")
            }) }
        composable (route = "Categoria"){Categoria(navCtrl)}
        composable  (route = "Frutas frescas") { FrutasFrescasScreen(viewModel, onClick = {
            navCtrl.navigate("Carrito")
        }) }
        composable  (route = "Verduras orgánicas") { VerdurasOrganicasScreen(viewModel, onClick = {
            navCtrl.navigate("Carrito")
        }) }
        composable  (route = "Carrito") { Carrito(navCtrl, viewModel) }
        composable  (route = "Confirmar pedido") { ConfirmarPedido() }
        composable  (route = "Finalizar Compra") { FinalizarCompra() }
    }
}