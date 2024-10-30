package com.foto.ai.domain.use_case.get_models

import com.foto.ai.data.repository.ModelRepositoryImpl
import com.foto.ai.data.retrofit.model.toModelDtoList
import com.foto.ai.data.room.dto.toModel
import com.foto.ai.domain.model.Model
import com.foto.ai.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetModelsUseCase @Inject constructor(
    private val repository: ModelRepositoryImpl
) {
    private suspend fun fetchAndCacheModels(userId: String) {
        val cacheResponse = repository.getCachedModels()
        if (cacheResponse.isEmpty()) {
            val apiResponse = repository.getModelsByUserId(userId)
            repository.cacheModels(apiResponse.toModelDtoList())
        }
    }

    fun executeGetModels(
        userId: String
    ): Flow<Resource<List<Model>>> = flow {
        try {
            emit(Resource.Loading())
            fetchAndCacheModels(userId)
            val models = repository.getCachedModels().map { it.toModel() }
            emit(Resource.Success(models))
        } catch (e: HttpException) {
            emit(Resource.Error(error = e, message = "Network error"))
        } catch (e: Exception) {
            emit(Resource.Error(error = e, message = "Error getting stations"))
        }
    }
}