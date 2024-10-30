package com.foto.ai.domain.repository

import com.foto.ai.data.retrofit.auth.AuthDto

interface AuthRepository {
    suspend fun getMobileToken(idToken: String, deviceId: String): AuthDto
}