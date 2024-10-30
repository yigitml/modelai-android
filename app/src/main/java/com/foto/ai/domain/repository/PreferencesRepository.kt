package com.foto.ai.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferencesManager {
    suspend fun <T> savePreference(key: Preferences.Key<T>, value: T)
    fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    fun isUserLoggedIn(): Flow<Boolean>
    suspend fun clearAuthData()
    suspend fun clearAllPreferences()
}

interface AppPreferencesRepository {
    // Auth Management
    suspend fun saveJwtToken(token: String)

    fun getJwtToken(): Flow<String>

    suspend fun saveRefreshToken(token: String)

    suspend fun saveTokenExpiry(timestamp: Long)

    fun isTokenExpired(): Flow<Boolean>

    // Device Management
    suspend fun saveDeviceId(deviceId: String)

    fun getDeviceId(): Flow<String>

    suspend fun saveFcmToken(token: String)

    // User Management
    suspend fun saveUserInfo(
        userId: String,
        email: String,
        name: String,
        avatar: String
    )

    fun getUserId(): Flow<String>

    fun getUserEmail(): Flow<String>

    fun getUserName(): Flow<String>

    // App State Management
    suspend fun updateLastSyncTime()

    fun getLastSyncTime(): Flow<Long>

    suspend fun incrementAppLaunchCount()

    fun getAppLaunchCount(): Flow<Int>

    suspend fun updateLastAppOpenTime()

    // Settings Management
    suspend fun setLanguage(languageCode: String)

    fun getLanguage(): Flow<String>

    suspend fun setBiometricEnabled(enabled: Boolean)

    fun isBiometricEnabled(): Flow<Boolean>

    // Cache Management
    suspend fun updateCacheExpiry(timeMillis: Long)

    fun isCacheExpired(): Flow<Boolean>

    // Auth State
    fun isUserLoggedIn(): Flow<Boolean>

    suspend fun logout()
}