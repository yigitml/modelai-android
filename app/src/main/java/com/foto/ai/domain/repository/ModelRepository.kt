package com.foto.ai.domain.repository

import com.foto.ai.data.retrofit.dto.model.ModelsDto
import com.foto.ai.data.retrofit.dto.model.ModelsDtoItem
import com.foto.ai.data.room.dto.ModelDto

interface ModelRepository {
    suspend fun getModelById(id: String): ModelsDtoItem
    suspend fun getModelsByUserId(userId: String): ModelsDto
    suspend fun cacheModels(models: List<ModelDto>)
    suspend fun getCachedModels(): List<ModelDto>
}