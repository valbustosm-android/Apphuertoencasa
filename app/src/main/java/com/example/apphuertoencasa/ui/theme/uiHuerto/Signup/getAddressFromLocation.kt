package com.example.apphuertoencasa.location

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.os.Build
import java.util.Locale

fun getAddressFromLocation(
    context: Context,
    location: Location,
    onResult: (String) -> Unit
) {
    val geocoder = Geocoder(context, Locale.getDefault())

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        ) { addresses ->
            val line = addresses.firstOrNull()?.getAddressLine(0).orEmpty()
            onResult(line)
        }
    } else {
        @Suppress("DEPRECATION")
        val addresses = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        )
        val line = addresses?.firstOrNull()?.getAddressLine(0).orEmpty()
        onResult(line)
    }
}

// Se dan los permisos para realizar la geolocalización,
//Geocoder para obtener la dirección, necesita la últma localización obtenible.
//Agarra la última latitud y longitud y agarra la dirección donde estás