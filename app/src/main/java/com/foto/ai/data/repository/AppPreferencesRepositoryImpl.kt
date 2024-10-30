package com.foto.ai.data.repository

import com.foto.ai.domain.repository.AppPreferencesRepository
import com.foto.ai.domain.repository.PreferencesManager
import com.foto.ai.util.PreferenceKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferencesRepositoryImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
): AppPreferencesRepository {
    // Auth Management
    override suspend fun saveJwtToken(token: String) =
        preferencesManager.savePreference(PreferenceKeys.JWT_TOKEN, token)

    override fun getJwtToken(): Flow<String> =
        preferencesManager.getPreference(PreferenceKeys.JWT_TOKEN, "")

    override suspend fun saveRefreshToken(token: String) =
        preferencesManager.savePreference(PreferenceKeys.REFRESH_TOKEN, token)

    override suspend fun saveTokenExpiry(timestamp: Long) =
        preferencesManager.savePreference(PreferenceKeys.TOKEN_EXPIRY, timestamp)

    override fun isTokenExpired(): Flow<Boolean> = getTokenExpiry()
        .map { expiry -> expiry < System.currentTimeMillis() }

    private fun getTokenExpiry(): Flow<Long> =
        preferencesManager.getPreference(PreferenceKeys.TOKEN_EXPIRY, 0L)

    // Device Management
    override suspend fun saveDeviceId(deviceId: String) =
        preferencesManager.savePreference(PreferenceKeys.DEVICE_ID, deviceId)

    override fun getDeviceId(): Flow<String> =
        preferencesManager.getPreference(PreferenceKeys.DEVICE_ID, "")

    override suspend fun saveFcmToken(token: String) =
        preferencesManager.savePreference(PreferenceKeys.FCM_TOKEN, token)

    // User Management
    override suspend fun saveUserInfo(
        userId: String,
        email: String,
        name: String,
        avatar: String
    ) {
        preferencesManager.savePreference(PreferenceKeys.USER_ID, userId)
        preferencesManager.savePreference(PreferenceKeys.USER_EMAIL, email)
        preferencesManager.savePreference(PreferenceKeys.USER_NAME, name)
        preferencesManager.savePreference(PreferenceKeys.USER_AVATAR, avatar)
    }

    override fun getUserId(): Flow<String> =
        preferencesManager.getPreference(PreferenceKeys.USER_ID, "")

    override fun getUserEmail(): Flow<String> =
        preferencesManager.getPreference(PreferenceKeys.USER_EMAIL, "")

    override fun getUserName(): Flow<String> =
        preferencesManager.getPreference(PreferenceKeys.USER_NAME, "")

    // App State Management
    override suspend fun updateLastSyncTime() =
        preferencesManager.savePreference(PreferenceKeys.LAST_SYNC_TIME, System.currentTimeMillis())

    override fun getLastSyncTime(): Flow<Long> =
        preferencesManager.getPreference(PreferenceKeys.LAST_SYNC_TIME, 0L)

    override suspend fun incrementAppLaunchCount() {
        getAppLaunchCount().first().let { count ->
            preferencesManager.savePreference(PreferenceKeys.APP_LAUNCH_COUNT, count + 1)
        }
    }

    override fun getAppLaunchCount(): Flow<Int> =
        preferencesManager.getPreference(PreferenceKeys.APP_LAUNCH_COUNT, 0)

    override suspend fun updateLastAppOpenTime() =
        preferencesManager.savePreference(
            PreferenceKeys.LAST_APP_OPEN_TIME,
            System.currentTimeMillis()
        )

    // Settings Management
    override suspend fun setLanguage(languageCode: String) =
        preferencesManager.savePreference(PreferenceKeys.LANGUAGE_CODE, languageCode)

    override fun getLanguage(): Flow<String> =
        preferencesManager.getPreference(PreferenceKeys.LANGUAGE_CODE, "en")

    override suspend fun setBiometricEnabled(enabled: Boolean) =
        preferencesManager.savePreference(PreferenceKeys.BIOMETRIC_ENABLED, enabled)

    override fun isBiometricEnabled(): Flow<Boolean> =
        preferencesManager.getPreference(PreferenceKeys.BIOMETRIC_ENABLED, false)

    // Cache Management
    override suspend fun updateCacheExpiry(timeMillis: Long) =
        preferencesManager.savePreference(PreferenceKeys.CACHE_EXPIRY, timeMillis)

    override fun isCacheExpired(): Flow<Boolean> = getCacheExpiry()
        .map { expiry -> expiry < System.currentTimeMillis() }

    private fun getCacheExpiry(): Flow<Long> =
        preferencesManager.getPreference(PreferenceKeys.CACHE_EXPIRY, 0L)

    // Auth State
    override fun isUserLoggedIn(): Flow<Boolean> = preferencesManager.isUserLoggedIn()

    override suspend fun logout() = preferencesManager.clearAuthData()
}