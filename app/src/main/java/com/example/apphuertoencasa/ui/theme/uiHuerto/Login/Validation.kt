package com.example.apphuertoencasa.ui.theme.uiHuerto.Login

import android.util.Patterns


fun validationEmail(email: String): Pair<Boolean, String> {
    return when {
        email.isEmpty() -> Pair(false, "El correo es requerido")
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair(false, "Correo inválido")
        !email.endsWith("@gmail.com") -> Pair(false, "Ese email no es el corporativo")
        else -> Pair(true, "")
    }
}

fun validatePassword(password: String): Pair<Boolean, String> {
    return when {
        password.isEmpty() -> Pair(false, "La contraseña es requerida")
        password.length < 8 -> Pair(false, "La contraseña debe tener al menos 8 caracteres")
        !password.any { it.isDigit() } -> Pair(false, "La contraseña debe tener al menos un número")
        else -> Pair(true, "")
    }
}

fun validateName(name: String): Pair<Boolean, String> {
    return when {
        name.isEmpty() -> Pair(false, "El nombre es requerido")
        name.length < 3 -> Pair(false, "El nombre debe tener al menos 3 caracteres")
        else -> Pair(true, "")
    }
}

fun validateConfirmPassword(password: String, confirmPassword: String): Pair<Boolean, String> {
    return when {
        confirmPassword.isEmpty() -> Pair(false, "La confirmación es requerida")
        confirmPassword.length < 8 -> Pair(false, "La contraseña debe tener al menos 8 caracteres")
        confirmPassword != password -> Pair(false, "Las contraseñas no coinciden")
        else -> Pair(true, "")
    }
}

fun validateAddress(address: String): Pair<Boolean, String> {
    return when {
        address.isEmpty() ->
            Pair(false, "La dirección es requerida")
        address.length < 5 ->
            Pair(false, "La dirección es muy corta")
        !address.any { it.isLetter() } ->
            Pair(false, "La dirección debe contener letras")
        !address.any { it.isDigit() } ->
            Pair(false, "Agrega un número (ej: número de calle o depto)")
        !Regex("""^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ0-9 #.,\-]+$""").matches(address) ->
            Pair(false, "La dirección contiene caracteres no válidos")
        else -> Pair(true, "")
    }
}

