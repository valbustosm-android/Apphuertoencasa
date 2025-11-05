package com.example.apphuertoencasa.ui.theme.uiHuerto

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apphuertoencasa.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth

@Composable
fun Login( navCtrl: NavHostController, modifier: Modifier, /*onSuccessfullLong:()-> Unit ={}*/) {
    var inputEmail by rememberSaveable { mutableStateOf("") }
    var inputPassword by rememberSaveable { mutableStateOf("") }
    val activity = LocalView.current.context as Activity
    var loginError by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf("") }

    val auth = Firebase.auth
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        containerColor = Color.White,
        contentColor = Color.Black
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 30.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(
                    R.drawable.huerto,
                ),
                contentDescription = "Content description for visually impaired",
                modifier = Modifier.size(150.dp)
            )
            Row (modifier = Modifier.fillMaxWidth()) {
                Text("Iniciar sesión", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }

            Row (modifier = Modifier.fillMaxWidth()) {
                Text("Hi there! Nice to see you again", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.LightGray)
            }

            Row (modifier = Modifier.padding(top = 30.dp)) {
                Column {
                    Text(
                        "Email", color = Color(0xFFF85F6A),
                        fontSize = 15.sp, fontWeight = FontWeight.Bold,
                    )
                    HuertoTextField(
                        value = inputEmail,
                        onValueChange = { inputEmail = it },
                        label = { Text("Escribe tu email") },
                        placeholder = {  },
                        supportingText = {
                            if(emailError.isNotEmpty()){
                                Text(
                                    text = emailError,
                                    color = Color.Red
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        "Password", color = Color(0xFFF85F6A),
                        fontSize = 15.sp, fontWeight = FontWeight.Bold
                    )
                    HuertoTextField(
                        value = inputPassword,
                        onValueChange = { inputPassword = it },
                        label = { Text("Escribe tu contraseña") },
                        placeholder = {  },
                        supportingText = {
                            if(passwordError.isNotEmpty()){
                                Text(
                                    text = passwordError,
                                    color = Color.Red
                                )
                            }
                        }
                    )
                }

            }

            Spacer(modifier = Modifier.padding(top = 50.dp))
            if(loginError.isNotEmpty()){
                Text(
                    text = loginError,
                    color = Color.Red,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }
            AppButton(

                text = "Inicio de sesión",
                onClick = {
                    navCtrl.navigate(route = "Categoria")
              /*val isValidEmail: Boolean = validationEmail(inputEmail).first
                    val isValidPassword: Boolean = validatePassword(inputPassword).first

                    emailError = validationEmail(inputEmail).second
                    passwordError =validatePassword(inputPassword).second

                    if(isValidEmail && isValidPassword){
                        auth.signInWithEmailAndPassword(inputEmail, inputPassword
                        ).addOnCompleteListener(activity){ task ->
                            if(task.isSuccessful){
                                onSuccessfullLong()
                            }else{
                                loginError = when(task.exception){
                                    is FirebaseAuthInvalidCredentialsException -> "Correo contraseña incorrecta"
                                    is FirebaseAuthInvalidUserException -> "No existe una cuenta con este correo"
                                    else -> "Error al iniciar sesión. Intente de nuevo"
                                }
                            }
                        }
                    }else{
                    }*/
                },
                height = 80.dp,
                containerColor = Color(0xFF629C44),
                contentColor = Color.White
            )
            Spacer(modifier = Modifier.padding(top = 30.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AppSocialMediaButton(
                    text = "Twitter",
                    onClick = { /* ... */ },
                    height = 30.dp,
                    containerColor = Color(0xFF1DA1F2),
                    contentColor = Color.White
                )
                AppSocialMediaButton(
                    text = "Facebook",
                    onClick = { /* ... */ },
                    height = 30.dp,
                    containerColor = Color(0xFF3B5998),
                    contentColor = Color.White
                )
            }
            Row (modifier = Modifier.padding(top = 60.dp).fillMaxWidth(),
                 horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
                Text("No tienes una cuenta?",
                color = Color(0xFF989EB1))
                Text(
                    text = "Regístrate",
                    color = Color(0xFF629C44),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable{
                        navCtrl.navigate(route = "Sign In")
                    }
                )
            }
        }
    }
}
