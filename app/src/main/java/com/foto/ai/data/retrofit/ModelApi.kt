package com.foto.ai.data.retrofit

import com.foto.ai.domain.model.Model
import retrofit2.http.GET
import retrofit2.http.Query

interface ModelApi {
    @GET("models")
    suspend fun getModelById(
        @Query("id") id: String
    ): Model

    @GET("models")
    suspend fun getModelsByUserId(
        @Query("userId") userId: String
    ): List<Model>
}