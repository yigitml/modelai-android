package com.foto.ai.data.retrofit.dto.photo

data class PhotosDtoItem(
    val id: String,
    val url: String,
    val createdAt: String,
    val userId: String,
    val modelId: String
)