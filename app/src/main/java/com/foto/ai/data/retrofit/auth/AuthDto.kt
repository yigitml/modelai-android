package com.foto.ai.data.retrofit.auth

data class AuthDto(
    val token: String,
    val user: AuthUser,
    val expiresAt: Int
) {
    data class AuthUser(
        val id: String,
        val email: String,
        val name: String,
        val avatarUrl: String,
    )
}