package com.foto.ai.data.repository

import com.foto.ai.data.retrofit.auth.AuthApi
import com.foto.ai.data.retrofit.auth.AuthDto
import com.foto.ai.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun getMobileToken(
        idToken: String,
        deviceId: String
    ): AuthDto =
        authApi.getMobileToken(idToken, deviceId)
}