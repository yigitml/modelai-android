package com.foto.ai.data.retrofit.user

import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun getUserById(
        @Query("id") id: String
    ): UsersDtoItem

    @GET("users")
    suspend fun getUsersByEmail(
        @Query("email") email: String
    ): UsersDtoItem
}