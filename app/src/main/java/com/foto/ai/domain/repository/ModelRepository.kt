package com.foto.ai.domain.repository

import com.foto.ai.data.room.dto.ModelDto
import com.foto.ai.domain.model.Model

interface ModelRepository {
    suspend fun getModelById(id: String): Model
    suspend fun getModelsByUserId(userId: String): List<Model>
    suspend fun cacheModels(models: List<ModelDto>)
    suspend fun getCachedModels(): List<ModelDto>
}