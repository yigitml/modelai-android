package com.foto.ai.data.repository

import com.foto.ai.data.retrofit.PhotoApi
import com.foto.ai.data.room.dao.PhotoDao
import com.foto.ai.data.room.dto.PhotoDto
import com.foto.ai.domain.model.Photo
import com.foto.ai.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
  private val photoApi: PhotoApi,
  private val photoDao: PhotoDao
): PhotoRepository {
  override suspend fun getPhotoById(id: String): Photo =
    photoApi.getPhotoById(id)

  override suspend fun getPhotosByModelId(modelId: String): List<Photo> =
    photoApi.getPhotosByModelId(modelId)

  override suspend fun cachePhotos(photo: List<PhotoDto>) =
    photoDao.insertPhotos(*photo.toTypedArray())

  override suspend fun getCachedPhotos(): List<PhotoDto> =
    photoDao.getPhotos()
}