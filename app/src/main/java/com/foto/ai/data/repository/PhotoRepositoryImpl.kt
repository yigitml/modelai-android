package com.foto.ai.data.repository

import com.foto.ai.data.retrofit.photo.PhotoApi
import com.foto.ai.data.retrofit.photo.PhotosDto
import com.foto.ai.data.retrofit.photo.PhotosDtoItem
import com.foto.ai.data.room.dao.PhotoDao
import com.foto.ai.data.room.dto.PhotoDto
import com.foto.ai.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
  private val photoApi: PhotoApi,
  private val photoDao: PhotoDao
) : PhotoRepository {
  override suspend fun getPhotoById(id: String): PhotosDtoItem =
    photoApi.getPhotoById(id)

  override suspend fun getPhotosByModelId(modelId: String): PhotosDto =
    photoApi.getPhotosByModelId(modelId)

  override suspend fun getPhotosByUserId(userId: String): PhotosDto =
    photoApi.getPhotosByUserId(userId)

  override suspend fun cachePhotos(photo: List<PhotoDto>) =
    photoDao.insertPhotos(*photo.toTypedArray())

  override suspend fun getCachedPhotos(): List<PhotoDto> =
    photoDao.getPhotos()
}