package com.foto.ai.data.retrofit.model

import retrofit2.http.GET
import retrofit2.http.Query

interface ModelApi {
    @GET("models")
    suspend fun getModelById(
        @Query("id") id: String
    ): ModelsDtoItem

    @GET("models")
    suspend fun getModelsByUserId(
        @Query("userId") userId: String
    ): ModelsDto
}