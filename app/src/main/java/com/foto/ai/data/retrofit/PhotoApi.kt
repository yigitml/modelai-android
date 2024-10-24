package com.foto.ai.data.retrofit

import com.foto.ai.domain.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("photos")
    suspend fun getPhotoById(
        @Query("id") id: String
    ): Photo

    @GET("photos")
    suspend fun getPhotosByModelId(
        @Query("modelId") modelId: String
    ): List<Photo>
}