package com.foto.ai.domain.model

import com.foto.ai.data.room.dto.ModelDto

data class Model(
    val avatarUrl: String,
    val createdAt: String,
    val description: String,
    val id: String,
    val name: String,
    val replicateId: String,
    val userId: String,
    val versionId: String
) {
    fun Model.toModelDtoList(modelList: List<Model>): List<ModelDto> = modelList.map {
        ModelDto(
            avatarUrl = it.avatarUrl,
            createdAt = it.createdAt,
            description = it.description,
            id = it.id,
            name = it.name,
            replicateId = it.replicateId,
            userId = it.userId,
            versionId = it.versionId
        )
    }
}