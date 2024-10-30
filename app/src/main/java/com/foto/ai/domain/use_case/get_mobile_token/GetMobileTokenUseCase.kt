package com.foto.ai.domain.use_case.get_mobile_token

import com.foto.ai.data.repository.AuthRepositoryImpl
import com.foto.ai.data.retrofit.auth.AuthDto
import com.foto.ai.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetMobileTokenUseCase @Inject constructor(
    private val repository: AuthRepositoryImpl
) {
    fun executeGetMobileToken(
        idToken: String,
        deviceId: String
    ): Flow<Resource<AuthDto>> = flow {
        try {

        } catch (e: HttpException) {
            emit(Resource.Error(error = e, message = "Network error"))
        } catch (e: Exception) {
            emit(Resource.Error(error = e, message = "Error getting stations"))
        }
    }
}