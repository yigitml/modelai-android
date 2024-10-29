package com.foto.ai.domain.use_case.get_photos

import com.foto.ai.data.repository.PhotoRepositoryImpl
import com.foto.ai.data.retrofit.dto.photo.toPhotoDtoList
import com.foto.ai.data.retrofit.dto.photo.toPhotoList
import com.foto.ai.domain.model.Photo
import com.foto.ai.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepositoryImpl
) {
    private suspend fun fetchAndCachePhotos(modelId: String) {
        val cacheResponse = repository.getCachedPhotos()
        if (cacheResponse.isEmpty()) {
            val apiResponse = repository.getPhotosByModelId(modelId)
            repository.cachePhotos(apiResponse.toPhotoDtoList())
        }
    }

    fun executeGetPhotos(
        modelId: String
    ): Flow<Resource<List<Photo>>> = flow {
        try {
            emit(Resource.Loading())
            //fetchAndCachePhotos(modelId)
            //val photos = repository.getCachedPhotos().map { it.toPhoto() }
            val photos = repository.getPhotosByModelId(modelId).toPhotoList()
            emit(Resource.Success(photos))
        } catch (e: HttpException) {
            emit(Resource.Error(error = e, message = "Network error"))
        } catch (e: Exception) {
            emit(Resource.Error(error = e, message = "Error getting stations"))
        }
    }
}