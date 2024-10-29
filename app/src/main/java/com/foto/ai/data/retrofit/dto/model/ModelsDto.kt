package com.foto.ai.data.retrofit.dto.model

import com.foto.ai.data.room.dto.ModelDto
import com.foto.ai.domain.model.Model

class ModelsDto : ArrayList<ModelsDtoItem>()

fun ModelsDto.toModelList(): List<Model> =
    this.map {
        Model(
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

fun ModelsDto.toModelDtoList(): List<ModelDto> =
    this.map {
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

