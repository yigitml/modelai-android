package com.foto.ai.di

import com.foto.ai.data.retrofit.ModelApi
import com.foto.ai.data.retrofit.PhotoApi
import com.foto.ai.data.retrofit.UserApi
import com.foto.ai.util.RetrofitConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RetrofitConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideModelApi(retrofit: Retrofit): ModelApi =
        retrofit.create(ModelApi::class.java)

    @Provides
    @Singleton
    fun providePhotoApi(retrofit: Retrofit): PhotoApi =
        retrofit.create(PhotoApi::class.java)
}