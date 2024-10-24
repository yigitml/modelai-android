package com.foto.ai.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.foto.ai.data.room.dto.UserDto

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg user: UserDto)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): UserDto
}