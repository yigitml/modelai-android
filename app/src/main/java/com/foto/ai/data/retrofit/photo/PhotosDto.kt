package com.foto.ai.data.retrofit.photo

import com.foto.ai.data.room.dto.PhotoDto
import com.foto.ai.domain.model.Photo

class PhotosDto : ArrayList<PhotosDtoItem>()

fun PhotosDto.toPhotoList(): List<Photo> =
    this.map {
        Photo(
            createdAt = it.createdAt,
            id = it.id,
            url = it.url,
            userId = it.userId,
            modelId = it.modelId
        )
    }

fun PhotosDto.toPhotoDtoList(): List<PhotoDto> =
    this.map {
        PhotoDto(
            createdAt = it.createdAt,
            id = it.id,
            url = it.url,
            userId = it.userId,
            modelId = it.modelId
        )
    }
