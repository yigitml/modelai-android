package com.foto.ai.domain.repository

import com.foto.ai.data.room.dto.UserDto
import com.foto.ai.domain.model.User

interface UserRepository {
    suspend fun getUserById(id: String): User
    suspend fun getUserByEmail(email: String): User
    suspend fun cacheUser(user: UserDto)
    suspend fun getCachedUserById(id: String): UserDto
}