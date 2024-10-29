package com.foto.ai.data.repository

import com.foto.ai.data.retrofit.ModelApi
import com.foto.ai.data.retrofit.dto.model.ModelsDto
import com.foto.ai.data.retrofit.dto.model.ModelsDtoItem
import com.foto.ai.data.room.dao.ModelDao
import com.foto.ai.data.room.dto.ModelDto
import com.foto.ai.domain.repository.ModelRepository
import javax.inject.Inject

class ModelRepositoryImpl @Inject constructor(
  private val modelApi: ModelApi,
  private val modelDao: ModelDao
) : ModelRepository {
  override suspend fun getModelById(id: String): ModelsDtoItem =
    modelApi.getModelById(id)

  override suspend fun getModelsByUserId(userId: String): ModelsDto =
    modelApi.getModelsByUserId(userId)

  override suspend fun cacheModels(models: List<ModelDto>) =
    modelDao.insertModels(*models.toTypedArray())

  override suspend fun getCachedModels(): List<ModelDto> =
    modelDao.getModels()
}