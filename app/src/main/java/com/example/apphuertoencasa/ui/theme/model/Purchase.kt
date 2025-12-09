package com.example.apphuertoencasa.ui.theme.model

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseItem(
    val name: String,
    val quantity: Int,
    val pricePerUnit: Int,
    val total: Int
)

@Serializable
data class Purchase(
    val id: String,
    val date: String,
    val items: List<PurchaseItem>,
    val totalPrice: Int
)

