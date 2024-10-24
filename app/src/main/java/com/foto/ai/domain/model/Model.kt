package com.foto.ai.domain.model

data class Model(
    val avatarUrl: String,
    val createdAt: String,
    val description: String,
    val id: String,
    val name: String,
    val replicateId: String,
    val userId: String,
    val versionId: String
)