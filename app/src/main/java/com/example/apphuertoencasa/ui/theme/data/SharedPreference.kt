package com.example.apphuertoencasa.ui.theme.data

import android.content.Context
import androidx.core.content.edit

class MyPrefs(context: Context) {
    private val prefs = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_EMAIL = "KEY_EMAIL"
        private const val KEY_ADDRESS = "KEY_ADDRESS"
        private const val KEY_PASSWORD = "KEY_PASSWORD"
        private const val KEY_CONFIRM_PASSWORD = "KEY_CONFIRM_PASSWORD"
        private const val KEY_IS_REGISTERED = "KEY_IS_REGISTERED"
    }

    fun saveName(value: String) = prefs.edit { putString(KEY_NAME, value) }
    fun saveEmail(value: String) = prefs.edit { putString(KEY_EMAIL, value) }
    fun saveAddress(value: String) = prefs.edit { putString(KEY_ADDRESS, value) }
    fun savePassword(value: String) = prefs.edit { putString(KEY_PASSWORD, value) }
    fun saveConfirmPassword(value: String) = prefs.edit { putString(KEY_CONFIRM_PASSWORD, value) }
   // sería el estado
    fun isRegistered(): Boolean = prefs.getBoolean(KEY_IS_REGISTERED, false)
    fun setRegistered(v: Boolean) = prefs.edit { putBoolean(KEY_IS_REGISTERED, v) }
}
