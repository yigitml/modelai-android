package com.foto.ai.domain.repository

import com.foto.ai.data.retrofit.user.UsersDtoItem
import com.foto.ai.data.room.dto.UserDto

interface UserRepository {
    suspend fun getUserById(id: String): UsersDtoItem
    suspend fun getUserByEmail(email: String): UsersDtoItem
    suspend fun cacheUser(user: UserDto)
    suspend fun getCachedUserById(id: String): UserDto
}