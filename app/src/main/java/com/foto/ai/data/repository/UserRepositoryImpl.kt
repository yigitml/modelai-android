package com.foto.ai.data.repository

import com.foto.ai.data.retrofit.UserApi
import com.foto.ai.data.room.dao.UserDao
import com.foto.ai.data.room.dto.UserDto
import com.foto.ai.domain.model.User
import com.foto.ai.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
  private val userApi: UserApi,
  private val userDao: UserDao
) : UserRepository {
  override suspend fun getUserById(id: String): User =
    userApi.getUserById(id)

  override suspend fun getUserByEmail(email: String): User =
    userApi.getUsersByEmail(email)

  override suspend fun cacheUser(user: UserDto) =
    userDao.insertUser(user)

  override suspend fun getCachedUserById(id: String): UserDto =
    userDao.getUserById(id)
}