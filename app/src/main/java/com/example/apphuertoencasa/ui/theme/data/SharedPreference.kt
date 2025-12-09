package com.example.apphuertoencasa.ui.theme.data

import android.content.Context
import androidx.core.content.edit
import com.example.apphuertoencasa.ui.theme.model.Purchase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MyPrefs(context: Context) {
    private val prefs = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    companion object {
        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_EMAIL = "KEY_EMAIL"
        private const val KEY_ADDRESS = "KEY_ADDRESS"
        private const val KEY_PASSWORD = "KEY_PASSWORD"
        private const val KEY_CONFIRM_PASSWORD = "KEY_CONFIRM_PASSWORD"
        private const val KEY_IS_REGISTERED = "KEY_IS_REGISTERED"
        private const val KEY_PURCHASES = "KEY_PURCHASES"
        private const val KEY_LAST_EMAIL = "KEY_LAST_EMAIL"
    }

    fun saveName(value: String) = prefs.edit { putString(KEY_NAME, value) }
    fun getName(): String? = prefs.getString(KEY_NAME, null)
    fun saveEmail(value: String) = prefs.edit { putString(KEY_EMAIL, value) }
    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)
    fun saveAddress(value: String) = prefs.edit { putString(KEY_ADDRESS, value) }
    fun savePassword(value: String) = prefs.edit { putString(KEY_PASSWORD, value) }
    fun saveConfirmPassword(value: String) = prefs.edit { putString(KEY_CONFIRM_PASSWORD, value) }
   // sería el estado
    fun isRegistered(): Boolean = prefs.getBoolean(KEY_IS_REGISTERED, false)
    fun setRegistered(v: Boolean) = prefs.edit { putBoolean(KEY_IS_REGISTERED, v) }
    
    private fun getPurchasesKey(email: String): String {
        return "${KEY_PURCHASES}_$email"
    }
    
    fun savePurchase(purchase: Purchase) {
        val email = getEmail() ?: return
        val purchases = getPurchases().toMutableList()
        purchases.add(purchase)
        val jsonString = json.encodeToString(purchases)
        prefs.edit { putString(getPurchasesKey(email), jsonString) }
    }
    
    fun getPurchases(): List<Purchase> {
        val email = getEmail() ?: return emptyList()
        val jsonString = prefs.getString(getPurchasesKey(email), null) ?: return emptyList()
        return try {
            json.decodeFromString<List<Purchase>>(jsonString)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    fun clearPurchases() {
        val email = getEmail() ?: return
        prefs.edit { remove(getPurchasesKey(email)) }
    }
    
    fun clearAllPurchases() {
        // Limpiar todas las compras de todos los usuarios (útil cuando cambia el email)
        val allKeys = prefs.all.keys
        allKeys.forEach { key ->
            if (key.startsWith(KEY_PURCHASES)) {
                prefs.edit { remove(key) }
            }
        }
    }
    
    fun getLastEmail(): String? = prefs.getString(KEY_LAST_EMAIL, null)
    fun setLastEmail(email: String) = prefs.edit { putString(KEY_LAST_EMAIL, email) }
}
