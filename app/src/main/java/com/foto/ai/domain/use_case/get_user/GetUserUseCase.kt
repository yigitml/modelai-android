package com.foto.ai.domain.use_case.get_user

import com.foto.ai.data.repository.UserRepositoryImpl
import com.foto.ai.data.retrofit.dto.user.toUserDto
import com.foto.ai.data.room.dto.toUser
import com.foto.ai.domain.model.User
import com.foto.ai.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepositoryImpl
) {
    private suspend fun fetchAndCacheUser(id: String) {
        val apiResponse = repository.getUserById(id)
        repository.cacheUser(apiResponse.toUserDto())
    }

    fun executeGetUser(
        id: String
    ): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            fetchAndCacheUser(id)
            val user = repository.getCachedUserById(id).toUser()
            emit(Resource.Success(user))
        } catch (e: HttpException) {
            emit(Resource.Error(error = e, message = "Network error"))
        } catch (e: Exception) {
            emit(Resource.Error(error = e, message = "Error getting stations"))
        }
    }
}