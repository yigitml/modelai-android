package com.foto.ai.domain.model

data class User(
    val createdAt: String,
    val email: String,
    val googleId: String,
    val id: String,
    val image: String,
    val name: String
)