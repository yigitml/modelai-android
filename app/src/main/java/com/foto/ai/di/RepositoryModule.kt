package com.foto.ai.di

import com.foto.ai.data.repository.AppPreferencesRepositoryImpl
import com.foto.ai.data.repository.AuthRepositoryImpl
import com.foto.ai.data.repository.ModelRepositoryImpl
import com.foto.ai.data.repository.PhotoRepositoryImpl
import com.foto.ai.data.repository.UserRepositoryImpl
import com.foto.ai.data.retrofit.auth.AuthApi
import com.foto.ai.data.retrofit.model.ModelApi
import com.foto.ai.data.retrofit.photo.PhotoApi
import com.foto.ai.data.retrofit.user.UserApi
import com.foto.ai.data.room.dao.ModelDao
import com.foto.ai.data.room.dao.PhotoDao
import com.foto.ai.data.room.dao.UserDao
import com.foto.ai.domain.repository.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesAuthRepository(
        api: AuthApi
    ): AuthRepositoryImpl {
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesPreferencesRepository(
        preferencesManager: PreferencesManager
    ): AppPreferencesRepositoryImpl {
        return AppPreferencesRepositoryImpl(preferencesManager)
    }

    @Provides
    @Singleton
    fun providesUserRepository(
        api: UserApi,
        dao: UserDao
    ): UserRepositoryImpl {
        return UserRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun providesModelRepository(
        api: ModelApi,
        dao: ModelDao
    ): ModelRepositoryImpl {
        return ModelRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun providesPhotoRepository(
        api: PhotoApi,
        dao: PhotoDao
    ): PhotoRepositoryImpl {
        return PhotoRepositoryImpl(api, dao)
    }
}