package com.foto.ai.presentation.ui.screens.camera_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foto.ai.domain.use_case.get_models.GetModelsUseCase
import com.foto.ai.domain.use_case.get_photos.GetPhotosUseCase
import com.foto.ai.domain.use_case.get_user.GetUserUseCase
import com.foto.ai.domain.use_case.update_data.UpdateDataUseCase
import com.foto.ai.util.ObjectState.*
import com.foto.ai.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val getModelsUseCase: GetModelsUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateDataUseCase: UpdateDataUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CameraState())
    val state: State<CameraState> = _state

    init {
        updateData("111978088121482772727")
    }

    private fun getUser(id: String) {
        getUserUseCase.executeGetUser(id)
            .onEach { resource ->
                val user = resource.data
                when (resource) {
                    is Resource.Success -> {
                        if (user != null) {
                            _state.value = state.value.copy(
                                userState = Success(user)
                            )
                        } else {
                            _state.value = state.value.copy(
                                userState = Error(
                                    message = "User not found",
                                    error = Exception("User not found")
                                )
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            userState = Error(
                                message = resource.message ?: "Error",
                                error = resource.error ?: Exception()
                            )
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            userState = Loading()
                        )
                    }
                }
            }
    }

    private fun getModels(userId: String) {
        getModelsUseCase.executeGetModels(userId)
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            modelsState = Success(resource.data ?: emptyList())
                        )
                        if (_state.value.selectedModel == null) {
                            resource.data?.let {
                                if (it.isNotEmpty()) {
                                    _state.value = state.value.copy(
                                        selectedModel = it.first(),
                                        isModelSectionExpanded = false
                                    )
                                    getPhotos(it.first().id)
                                }
                            }
                        }
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            modelsState = Error(
                                message = resource.message ?: "Error",
                                error = resource.error ?: Exception()
                            )
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            modelsState = Loading()
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getPhotos(modelId: String) {
        getPhotosUseCase.executeGetPhotos(modelId)
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            photosState = Success(resource.data ?: emptyList())
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            photosState = Error(
                                message = resource.message ?: "Error",
                                error = resource.error ?: Exception()
                            )
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            photosState = Loading()
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun updateData(userId: String) {
        updateDataUseCase.executeUpdateData(userId)
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        getModels(userId)
                        getPhotos(userId)
                        getUser(userId)
                    }

                    is Resource.Error -> {
                    }

                    is Resource.Loading -> {
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun onEvent(event: CameraEvent) {
        when (event) {
            is CameraEvent.GetUser -> getUser(event.id)
            is CameraEvent.GetModels -> getModels(event.userId)
            is CameraEvent.GetPhotos -> getPhotos(event.modelId)
            is CameraEvent.UpdateData -> updateData(event.userId)
            is CameraEvent.OnModelClick -> {
                _state.value = state.value.copy(
                    selectedModel = event.model
                )
                getPhotos(event.model.id)
            }

            is CameraEvent.ChangeLayoutType -> {
                _state.value = state.value.copy(
                    isGridEnabled = event.isGridEnabled
                )
            }

            is CameraEvent.ChangeIsModelSectionExpanded -> {
                _state.value = state.value.copy(
                    isModelSectionExpanded = event.isModelSectionExpanded
                )
            }
        }
    }
}