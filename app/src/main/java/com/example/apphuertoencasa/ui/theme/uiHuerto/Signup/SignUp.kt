package com.example.apphuertoencasa.ui.theme.uiHuerto.Signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apphuertoencasa.ui.theme.data.MyPrefs
import com.example.apphuertoencasa.ui.theme.uiHuerto.AppButton
import com.example.apphuertoencasa.ui.theme.uiHuerto.HuertoTextField
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validateAddress
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validateConfirmPassword
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validateName
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validatePassword
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validationEmail


@Composable
fun SignUp(
    modifier: Modifier,
    navCtrl: NavHostController,
    onSuccessfullRegister: () -> Unit = {}
) {
    var inputEmail by rememberSaveable { mutableStateOf("") }
    var inputPassword by rememberSaveable { mutableStateOf("") }
    var inputName by rememberSaveable { mutableStateOf("") }
    var inputAddress by rememberSaveable { mutableStateOf("") }
    var inputConfirmarContrasena by rememberSaveable { mutableStateOf("") }

    var nameError by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf("") }
    var addressError by rememberSaveable { mutableStateOf("") }
    var confirmarPasswordError by rememberSaveable { mutableStateOf("") }
    var registerError by rememberSaveable { mutableStateOf("") }


    val context = LocalContext.current
    val prefs = remember { MyPrefs(context) }

   if (prefs.isRegistered()) {
        navCtrl.navigate("Categoria")
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        containerColor = Color.White,
        contentColor = Color.Black
    ) { inner ->
        val scroll = rememberScrollState()
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(inner)
                .imePadding()
                .verticalScroll(scroll)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.padding(top = 80.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Regístrate", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.padding(top = 40.dp))

            Text("Nombre", fontWeight = FontWeight.Bold)
            Row(Modifier.fillMaxWidth()) {
                HuertoTextField(
                    value = inputName,
                    onValueChange = {
                        inputName = it
                        val (ok, msg) = validateName(inputName)
                        nameError = if (ok || inputName.isEmpty()) "" else msg
                    },
                    label = { Text("Escribe tu nombre") },
                    placeholder = { },
                    supportingText = {
                        if (nameError.isNotEmpty()) Text(nameError, color = Color.Red)
                    }
                )
            }

            Text("Dirección", fontWeight = FontWeight.Bold)
            Row(Modifier.fillMaxWidth()) {
                HuertoTextField(
                    value = inputAddress,
                    onValueChange = {
                        inputAddress = it
                        val (ok, msg) = validateAddress(inputAddress)
                        addressError = if (ok || inputAddress.isEmpty()) "" else msg
                    },
                    label = { Text("Escribe tu dirección") },
                    placeholder = { },
                    supportingText = {
                        if (addressError.isNotEmpty()) Text(addressError, color = Color.Red)
                    }
                )
            }

            Text("Email", fontWeight = FontWeight.Bold)
            Row(Modifier.fillMaxWidth()) {
                HuertoTextField(
                    value = inputEmail,
                    onValueChange = {
                        inputEmail = it
                        val (ok, msg) = validationEmail(inputEmail)
                        emailError = if (ok || inputEmail.isEmpty()) "" else msg
                    },
                    label = { Text("Escribe tu email") },
                    placeholder = { },
                    supportingText = {
                        if (emailError.isNotEmpty()) Text(emailError, color = Color.Red)
                    }
                )
            }

            Text("Password", fontWeight = FontWeight.Bold)
            Row(Modifier.fillMaxWidth()) {
                HuertoTextField(
                    value = inputPassword,
                    onValueChange = {
                        inputPassword = it
                        val (ok, msg) = validatePassword(inputPassword)
                        passwordError = if (ok || inputPassword.isEmpty()) "" else msg
                    },
                    label = { Text("Escribe tu contraseña") },
                    placeholder = { },
                    supportingText = {
                        if (passwordError.isNotEmpty()) Text(passwordError, color = Color.Red)
                    }
                )
            }

            Text("Confirmar contraseña", fontWeight = FontWeight.Bold)
            Row(Modifier.fillMaxWidth()) {
                HuertoTextField(
                    value = inputConfirmarContrasena,
                    onValueChange = {
                        inputConfirmarContrasena = it
                        val (ok, msg) = validateConfirmPassword(
                            inputPassword,
                            inputConfirmarContrasena
                        )
                        confirmarPasswordError =
                            if (ok || inputConfirmarContrasena.isEmpty()) "" else msg
                    },
                    label = { Text("Confirma tu contraseña") },
                    placeholder = { },
                    supportingText = {
                        if (confirmarPasswordError.isNotEmpty()) Text(
                            confirmarPasswordError,
                            color = Color.Red
                        )
                    }
                )
            }
            if (registerError.isNotEmpty()) {
                Text(registerError, color = Color.Red)
            }

            AppButton(
                text = "Registro",
                onClick = {
                    val (isValidName, nameMsg) = validateName(inputName)
                    val (isValidAddress, addressMsg) = validateAddress(inputAddress)
                    val (isValidEmail, emailMsg) = validationEmail(inputEmail)
                    val (isValidPassword, passMsg) = validatePassword(inputPassword)
                    val (isValidConfirm, confirmMsg) = validateConfirmPassword(
                        inputPassword,
                        inputConfirmarContrasena
                    )

                    nameError = if (!isValidName) nameMsg else ""
                    addressError = if (!isValidAddress) addressMsg else ""
                    emailError = if (!isValidEmail) emailMsg else ""
                    passwordError = if (!isValidPassword) passMsg else ""
                    confirmarPasswordError = if (!isValidConfirm) confirmMsg else ""

                    if (isValidName && isValidAddress && isValidEmail && isValidPassword && isValidConfirm) {
                        prefs.saveName(inputName)
                        prefs.saveEmail(inputEmail)
                        prefs.saveAddress(inputAddress)
                        prefs.savePassword(inputPassword)
                        prefs.saveConfirmPassword(inputConfirmarContrasena)
                       prefs.setRegistered(true)
                        registerError = ""
                        onSuccessfullRegister()
                        navCtrl.navigate("Categoria")
                    } else {
                        registerError = "Revisa los campos marcados en rojo."
                    }
                },
                height = 80.dp,
                containerColor = Color(0xFF629C44),
                contentColor = Color.White,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                "Have an Account? Sign In",
                modifier = Modifier
                    .padding(start = 60.dp)
                    .padding(top = 30.dp)
                    .clickable { navCtrl.navigate("Login") }
            )
        }
    }
}