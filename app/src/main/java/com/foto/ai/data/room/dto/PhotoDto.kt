package com.foto.ai.data.room.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.foto.ai.domain.model.Photo

@Entity(tableName = "photos")
data class PhotoDto(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "model_id") val modelId: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "user_id") val userId: String
)

fun PhotoDto.toPhoto() = Photo(
    id = id,
    createdAt = createdAt,
    modelId = modelId,
    url = url,
    userId = userId
)