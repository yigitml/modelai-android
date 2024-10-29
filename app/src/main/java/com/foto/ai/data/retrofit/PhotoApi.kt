package com.foto.ai.data.retrofit

import com.foto.ai.data.retrofit.dto.photo.PhotosDto
import com.foto.ai.data.retrofit.dto.photo.PhotosDtoItem
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("photos")
    suspend fun getPhotoById(
        @Query("id") id: String
    ): PhotosDtoItem

    @GET("photos")
    suspend fun getPhotosByModelId(
        @Query("modelId") modelId: String
    ): PhotosDto

    @GET("photos")
    suspend fun getPhotosByUserId(
        @Query("userId") userId: String
    ): PhotosDto
}