package com.foto.ai.presentation.ui.screens.camera_screen

import com.foto.ai.domain.model.Model

sealed class CameraEvent {
    data class GetUser(val id: String) : CameraEvent()
    data class GetModels(val userId: String) : CameraEvent()
    data class GetPhotos(val modelId: String) : CameraEvent()

    data class UpdateData(val userId: String) : CameraEvent()

    data class OnModelClick(val model: Model) : CameraEvent()

    data class ChangeLayoutType(val isGridEnabled: Boolean) : CameraEvent()
    data class ChangeIsModelSectionExpanded(val isModelSectionExpanded: Boolean) : CameraEvent()
}