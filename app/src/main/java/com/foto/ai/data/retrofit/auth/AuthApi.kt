package com.foto.ai.data.retrofit.auth

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("api/auth/token/mobile")
    suspend fun getMobileToken(
        @Field("idToken") idToken: String,
        @Field("deviceId") deviceId: String
    ): AuthDto
}