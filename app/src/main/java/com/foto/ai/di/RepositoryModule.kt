package com.foto.ai.di

import com.foto.ai.data.repository.ModelRepositoryImpl
import com.foto.ai.data.repository.PhotoRepositoryImpl
import com.foto.ai.data.repository.UserRepositoryImpl
import com.foto.ai.data.retrofit.ModelApi
import com.foto.ai.data.retrofit.PhotoApi
import com.foto.ai.data.retrofit.UserApi
import com.foto.ai.data.room.dao.ModelDao
import com.foto.ai.data.room.dao.PhotoDao
import com.foto.ai.data.room.dao.UserDao
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