package com.foto.ai.data.room.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.foto.ai.domain.model.User

@Entity(tableName = "users")
data class UserDto(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "google_id") val googleId: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "name") val name: String
)

fun UserDto.toUser() = User(
    id = id,
    createdAt = createdAt,
    email = email,
    googleId = googleId,
    image = image,
    name = name
)