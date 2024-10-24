package com.foto.ai.data.room.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.foto.ai.domain.model.Model

@Entity(tableName = "models")
data class ModelDto(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "replicate_id") val replicateId: String,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "version_id") val versionId: String
)

fun ModelDto.toModel(): Model = Model(
    id = id,
    avatarUrl = avatarUrl,
    createdAt = createdAt,
    description = description,
    name = name,
    replicateId = replicateId,
    userId = userId,
    versionId = versionId
)
