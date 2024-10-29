package com.foto.ai.presentation.ui.screens.camera_screen.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.foto.ai.presentation.ui.screens.camera_screen.CameraEvent
import com.foto.ai.presentation.ui.screens.camera_screen.CameraViewModel
import com.foto.ai.presentation.ui.screens.camera_screen.view.components.LayoutTypeSection
import com.foto.ai.presentation.ui.screens.camera_screen.view.components.ModelSection
import com.foto.ai.presentation.ui.screens.camera_screen.view.components.PhotoSection
import com.foto.ai.presentation.ui.theme.AppTheme

@Composable
fun CameraScreen(
    modifier: Modifier,
    viewModel: CameraViewModel
) {
    val state = viewModel.state.value

    fun changeIsModelSectionExpanded(isModelSectionExpanded: Boolean) {
        viewModel.onEvent(CameraEvent.ChangeIsModelSectionExpanded(isModelSectionExpanded))
    }

    fun changeLayoutType(isGridEnabled: Boolean) {
        viewModel.onEvent(CameraEvent.ChangeLayoutType(isGridEnabled))
    }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            item {
                ModelSection(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    state.modelsState.data ?: emptyList(),
                    onModelClick = { model ->
                        viewModel.onEvent(CameraEvent.OnModelClick(model))
                    },
                    state.selectedModel,
                    state.isModelSectionExpanded,
                    onChangeLayoutType = {
                        changeIsModelSectionExpanded(it)
                    })
            }

            item {
                LayoutTypeSection(Modifier.fillMaxWidth(), state.isGridEnabled,
                    { changeLayoutType(it) })
            }

            item {
                PhotoSection(
                    Modifier.fillMaxWidth(),
                    state.photosState.data ?: emptyList(),
                    state.isGridEnabled
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCameraScreen() {
    AppTheme {
        CameraScreen(Modifier, hiltViewModel())
    }
}