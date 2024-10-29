package com.foto.ai.presentation.ui.screens.camera_screen

import androidx.compose.runtime.Immutable
import com.foto.ai.domain.model.Model
import com.foto.ai.domain.model.Photo
import com.foto.ai.domain.model.User
import com.foto.ai.util.ObjectState

@Immutable
data class CameraState(
    val selectedModel: Model? = null,

    val isGridEnabled: Boolean = true,
    val isModelSectionExpanded: Boolean = true,

    val userState: ObjectState<User> = ObjectState.Loading(),
    val modelsState: ObjectState<List<Model>> = ObjectState.Loading(),
    val photosState: ObjectState<List<Photo>> = ObjectState.Loading()
)