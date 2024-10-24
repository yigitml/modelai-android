package com.foto.ai.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.foto.ai.data.room.dto.ModelDto

@Dao
interface ModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(vararg models: ModelDto)

    @Query("SELECT * FROM models")
    suspend fun getModels(): List<ModelDto>
}