package com.foto.ai.domain.repository

import com.foto.ai.data.room.dto.PhotoDto
import com.foto.ai.domain.model.Photo

interface PhotoRepository {
    suspend fun getPhotoById(id: String): Photo
    suspend fun getPhotosByModelId(modelId: String): List<Photo>
    suspend fun cachePhotos(photo: List<PhotoDto>)
    suspend fun getCachedPhotos(): List<PhotoDto>
}