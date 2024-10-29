package com.foto.ai.domain.repository

import com.foto.ai.data.retrofit.dto.photo.PhotosDto
import com.foto.ai.data.retrofit.dto.photo.PhotosDtoItem
import com.foto.ai.data.room.dto.PhotoDto

interface PhotoRepository {
    suspend fun getPhotoById(id: String): PhotosDtoItem
    suspend fun getPhotosByModelId(modelId: String): PhotosDto
    suspend fun getPhotosByUserId(userId: String): PhotosDto
    suspend fun cachePhotos(photo: List<PhotoDto>)
    suspend fun getCachedPhotos(): List<PhotoDto>
}