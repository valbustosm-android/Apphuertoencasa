package com.example.apphuertoencasa.ui.theme.uiHuerto.Signup

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.apphuertoencasa.R
import com.example.apphuertoencasa.location.getAddressFromLocation
import com.example.apphuertoencasa.ui.theme.data.MyPrefs
import com.example.apphuertoencasa.ui.theme.uiHuerto.AppButton
import com.example.apphuertoencasa.ui.theme.uiHuerto.HuertoTextField
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validateAddress
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validateConfirmPassword
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validateName
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validatePassword
import com.example.apphuertoencasa.ui.theme.uiHuerto.Login.validationEmail
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

@Composable
fun SignUp(
    modifier: Modifier = Modifier,
    navCtrl: NavHostController,
    onSuccessfullRegister: () -> Unit = {}
) {
    // Agrego comentarios para entender el código
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
    val scope = rememberCoroutineScope()
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Función que obtiene la última ubicación y rellena la dirección
    fun fetchLocationAndSetAddress() {
        val hasFineLocation = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasCoarseLocation = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasFineLocation && !hasCoarseLocation) {
            Toast.makeText(
                context,
                "No hay permisos de ubicación (fetchLocationAndSetAddress).",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        Toast.makeText(
            context,
            "Buscando tu ubicación...",
            Toast.LENGTH_SHORT
        ).show()

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    Toast.makeText(
                        context,
                        "Ubicación obtenida: ${location.latitude}, ${location.longitude}",
                        Toast.LENGTH_SHORT
                    ).show()

                    scope.launch {
                        getAddressFromLocation(context, location) { address ->
                            if (address.isNotBlank()) {
                                inputAddress = address
                                val (ok, msg) = validateAddress(inputAddress)
                                addressError =
                                    if (ok || inputAddress.isEmpty()) "" else msg

                                Toast.makeText(
                                    context,
                                    "Dirección detectada.",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else {
                                Toast.makeText(
                                    context,
                                    "No se pudo obtener una dirección legible.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "location == null. El dispositivo no tiene una ubicación reciente. Abre Maps, espera unos segundos e inténtalo de nuevo.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Error al obtener ubicación: ${e.localizedMessage ?: "desconocido"}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    // Launcher para pedir permisos y luego obtener la ubicación,
    // acá da la ventana o Laucher en donde tienes que aceptar o no la ubicación
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (granted) {
            Toast.makeText(
                context,
                "Permiso de ubicación concedido, obteniendo dirección...",
                Toast.LENGTH_SHORT
            ).show()
            fetchLocationAndSetAddress()
        } else {
            Toast.makeText(
                context,
                "Se necesita permiso de ubicación para rellenar tu dirección automáticamente.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(80.dp)
            ) {
                Text("Regístrate", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Image(
                    painter = painterResource(R.drawable.huerto),
                    contentDescription = null,
                    modifier = Modifier.size(90.dp)
                )
            }

            Spacer(modifier = Modifier.padding(top = 40.dp))

            // Nombre
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

            // Dirección
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

            // Botón: usar ubicación actual
            TextButton(
                onClick = {
                    val hasFineLocation = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED

                    val hasCoarseLocation = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED

                    if (hasFineLocation || hasCoarseLocation) {
                        fetchLocationAndSetAddress()
                    } else {
                        locationPermissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Usar mi ubicación actual")
            }

            // Email
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

            // Password
            var passwordVisible by remember { mutableStateOf(false) }

             // Password
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
                    placeholder = {},
                    supportingText = {
                        if (passwordError.isNotEmpty()) {
                            Text(passwordError, color = Color.Red)
                        }
                    },
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                    trailingIcon = {
                        val icon = if (passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = icon,
                                contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                            )
                        }
                    }
                )
            }
            // Confirmar contraseña
            var confirmPasswordVisible by remember { mutableStateOf(false) }
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
                        if (confirmarPasswordError.isNotEmpty()) {
                            Text(confirmarPasswordError, color = Color.Red)
                        }
                    },
                    visualTransformation = if (confirmPasswordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                    trailingIcon = {
                        val icon = if (confirmPasswordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff

                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                imageVector = icon,
                                contentDescription = if (confirmPasswordVisible)
                                    "Ocultar contraseña"
                                else
                                    "Mostrar contraseña"
                            )
                        }
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
                        // Verificar si el email cambió
                        val lastEmail = prefs.getLastEmail()
                        if (lastEmail != null && lastEmail != inputEmail) {
                            // Si el email es diferente, borrar todas las compras
                            prefs.clearAllPurchases()
                        }
                        prefs.saveName(inputName)
                        prefs.saveEmail(inputEmail)
                        prefs.setLastEmail(inputEmail)
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