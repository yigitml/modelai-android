package com.foto.ai.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.foto.ai.data.room.dto.PhotoDto

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(vararg photo: PhotoDto)

    @Query("SELECT * FROM photos")
    suspend fun getPhotos(): List<PhotoDto>
}