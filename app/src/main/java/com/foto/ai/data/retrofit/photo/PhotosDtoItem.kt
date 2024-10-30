package com.foto.ai.data.retrofit.photo

data class PhotosDtoItem(
    val id: String,
    val url: String,
    val createdAt: String,
    val userId: String,
    val modelId: String
)