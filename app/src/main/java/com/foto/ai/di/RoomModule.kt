package com.foto.ai.di

import android.content.Context
import androidx.room.Room
import com.foto.ai.data.room.ApplicationDatabase
import com.foto.ai.util.RoomConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesApplicationDatabase(@ApplicationContext context: Context): ApplicationDatabase =
        Room.databaseBuilder(
            context,
            ApplicationDatabase::class.java,
            RoomConstants.DB_NAME
        ).fallbackToDestructiveMigrationFrom().build()

    @Provides
    @Singleton
    fun providesUserDao(db: ApplicationDatabase) =
        db.userDao()

    @Provides
    @Singleton
    fun providesModelDao(db: ApplicationDatabase) =
        db.modelDao()

    @Provides
    @Singleton
    fun providesPhotoDao(db: ApplicationDatabase) =
        db.photoDao()
}