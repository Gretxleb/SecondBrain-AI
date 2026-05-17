package com.secondbrain.ai.core.util

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SecureCredentialStore(context: Context) {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secondbrain_secure_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(key: String, token: String) {
        sharedPreferences.edit().putString(key, token).apply()
    }

    fun loadToken(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun clearToken(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}
