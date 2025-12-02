package com.example.apphuertoencasa.ui.theme.model

data class Fruta (
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    var stock: Int = 0,
    var contador: Int = 0,
    var precio: Int = 0
)