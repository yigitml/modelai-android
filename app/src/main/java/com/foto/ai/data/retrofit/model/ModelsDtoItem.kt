package com.foto.ai.data.retrofit.model

data class ModelsDtoItem(
    val id: String,
    val name: String,
    val description: String,
    val avatarUrl: String,
    val createdAt: String,
    val userId: String,
    val replicateId: String,
    val versionId: String
)