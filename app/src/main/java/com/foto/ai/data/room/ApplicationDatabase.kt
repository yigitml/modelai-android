package com.foto.ai.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.foto.ai.data.room.dao.ModelDao
import com.foto.ai.data.room.dao.PhotoDao
import com.foto.ai.data.room.dao.UserDao
import com.foto.ai.data.room.dto.ModelDto
import com.foto.ai.data.room.dto.PhotoDto
import com.foto.ai.data.room.dto.UserDto

@Database(
    entities = [UserDto::class, ModelDto::class, PhotoDto::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun modelDao(): ModelDao
    abstract fun photoDao(): PhotoDao
}