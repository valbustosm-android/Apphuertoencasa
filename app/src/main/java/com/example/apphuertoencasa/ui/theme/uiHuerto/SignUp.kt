package com.example.apphuertoencasa.ui.theme.uiHuerto

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth

@Composable
fun SignUp(modifier: Modifier, navCtrl: NavHostController, onSuccessfullRegister:()-> Unit ={}){

    var inputEmail by rememberSaveable { mutableStateOf("") }
    var inputPassword by rememberSaveable { mutableStateOf("") }
    var inputName by rememberSaveable { mutableStateOf("") }
    var inputAddress by rememberSaveable { mutableStateOf("") }
    var inputConfirmarContrasena by rememberSaveable { mutableStateOf("") }
    val activity = LocalView.current.context as Activity
    var loginError by rememberSaveable { mutableStateOf("") }
    var nameError by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf("") }
    var addressError by rememberSaveable { mutableStateOf("") }
    var confirmarPasswordError by rememberSaveable { mutableStateOf("") }
    var registerError by rememberSaveable { mutableStateOf("") }

    val auth = Firebase.auth


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        containerColor = Color.White,
        contentColor = Color.Black
    ) { inner->
        val scroll = rememberScrollState()
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(inner)
                .imePadding()
                .verticalScroll(scroll)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Spacer(modifier= Modifier.padding(top=80.dp))
            Row (verticalAlignment = Alignment.CenterVertically) { Text("Regístrate",
                fontSize = 30.sp, fontWeight = FontWeight.Bold ) }
            Spacer(modifier= Modifier.padding(top=40.dp))
            Text("Nombre", fontWeight = FontWeight.Bold )
            Row ( modifier = Modifier.fillMaxWidth()
            ) {
                HuertoTextField(
                    value = inputName,
                    onValueChange = { inputName= it },
                    label = { Text("Escribe tu nombre") },
                    placeholder = {  },
                    supportingText = {
                        if(nameError.isNotEmpty()){
                            Text(
                                text = nameError,
                                color = Color.Red
                            )
                        }
                    }
                )

            }
            Text("Dirección", fontWeight = FontWeight.Bold )
            Row (modifier = Modifier.fillMaxWidth()){
                HuertoTextField(
                    value = inputAddress,
                    onValueChange = { inputAddress= it },
                    label = { Text("Escribe tu dirección") },
                    placeholder = {  },
                    supportingText = {
                        Text(
                            text = addressError,
                            color = Color.Red
                        )
                    }
                )
            }

            Text("Email", fontWeight = FontWeight.Bold )
            Row (modifier = Modifier.fillMaxWidth()){
                HuertoTextField(
                    value = inputEmail,
                    onValueChange = { inputEmail= it },
                    label = { Text("Escribe tu email") },
                    placeholder = {  },
                    supportingText = {
                        Text(
                            text = emailError,
                            color = Color.Red
                        )
                    }
                )
            }
            Text("Password", fontWeight = FontWeight.Bold )
            Row (modifier = Modifier.fillMaxWidth()){
             HuertoTextField(
                value = inputPassword,
                onValueChange = { inputPassword = it },
                label = { Text("Escribe tu contraseña") },
                placeholder = {  },
                supportingText = {
                    Text(
                        text = passwordError,
                        color = Color.Red
                    )
                 }
               )
            }

            Text("Confirmar contraseña", fontWeight = FontWeight.Bold )
            Row (modifier = Modifier.fillMaxWidth()){
                HuertoTextField(
                    value = inputConfirmarContrasena,
                    onValueChange = { inputConfirmarContrasena = it },
                    label = { Text("Confirma tu contraseña") },
                    placeholder = {  },
                    supportingText = {
                        Text(
                            text = confirmarPasswordError,
                            color = Color.Red
                        )
                    }
                )
            }
            if(registerError.isNotEmpty()){
                Text(
                    text = registerError,
                    color = Color.Red
                )
            }

            AppButton(
                text = "Registro",
                onClick = {
                    val isValidName = validateName(inputName).first
                    val isValidAddress = validateAddress(inputAddress).first
                    val isValidConfirmPassword = validateConfirmPassword(inputConfirmarContrasena, inputPassword).first
                    val isValidEmail: Boolean = validationEmail(inputEmail).first
                    val isValidPassword: Boolean = validatePassword(inputPassword).first

                    emailError = validationEmail(inputEmail).second
                    passwordError =validatePassword(inputPassword).second
                    nameError = validateName(inputName).second
                    addressError = validateAddress(inputAddress).second
                    confirmarPasswordError = validateConfirmPassword(inputConfirmarContrasena, inputPassword).second

                    if(isValidName && isValidAddress && isValidEmail && isValidPassword && isValidConfirmPassword){
                        auth.createUserWithEmailAndPassword(inputEmail, inputPassword
                        ).addOnCompleteListener(activity) { task ->
                            if(task.isSuccessful){
                                onSuccessfullRegister()
                            } else {
                                registerError = when(task.isSuccessful){
                                    is FirebaseAuthInvalidCredentialsException -> "Correo inválido"
                                    is FirebaseAuthInvalidUserException -> "Correo ya registrado"
                                    else -> "Error al registrarse"
                                }
                            }
                        }
                    } else{
                        registerError = "Hubo un error en el registro"
                    }

                },
                height = 80.dp,
                containerColor = Color(0xFF629C44),
                contentColor = Color.White,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text("Have an Account? Sign In",
                modifier = Modifier
                    .padding(start = 60.dp)
                    .padding(top = 30.dp))
        }
    }
}