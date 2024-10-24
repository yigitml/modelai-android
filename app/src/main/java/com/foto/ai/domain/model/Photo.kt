package com.foto.ai.domain.model

data class Photo(
    val createdAt: String,
    val id: String,
    val modelId: String,
    val url: String,
    val userId: String
)