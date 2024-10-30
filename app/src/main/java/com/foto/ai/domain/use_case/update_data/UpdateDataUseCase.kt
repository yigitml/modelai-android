package com.foto.ai.domain.use_case.update_data

import com.foto.ai.data.repository.ModelRepositoryImpl
import com.foto.ai.data.repository.UserRepositoryImpl
import com.foto.ai.data.retrofit.model.toModelDtoList
import com.foto.ai.data.retrofit.user.toUserDto
import com.foto.ai.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class UpdateDataUseCase @Inject constructor(
    private val userRepository: UserRepositoryImpl,
    private val modelRepository: ModelRepositoryImpl
) {
    fun executeUpdateData(
        userId: String
    ): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val apiUser = userRepository.getUserById(userId)
            val apiModels = modelRepository.getModelsByUserId(userId)

            userRepository.cacheUser(apiUser.toUserDto())
            modelRepository.cacheModels(apiModels.toModelDtoList())

            emit(Resource.Success(Unit))
        } catch (e: HttpException) {
            emit(Resource.Error(error = e, message = "Network error"))
        } catch (e: Exception) {
            emit(Resource.Error(error = e, message = "Error getting stations"))
        }
    }
}