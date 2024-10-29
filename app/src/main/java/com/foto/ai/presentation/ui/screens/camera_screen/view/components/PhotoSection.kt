package com.foto.ai.presentation.ui.screens.camera_screen.view.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.foto.ai.domain.model.Photo

@Composable
fun PhotoSection(
    modifier: Modifier = Modifier,
    photos: List<Photo> = emptyList(),
    isGridEnabled: Boolean
) {
    AnimatedContent(
        targetState = isGridEnabled,
        transitionSpec = {
            if (targetState) {
                slideInHorizontally { width -> width } + fadeIn() togetherWith
                        slideOutHorizontally { width -> -width } + fadeOut()
            } else {
                slideInHorizontally { width -> width } + fadeIn() togetherWith
                        slideOutHorizontally { width -> -width } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }, label = ""
    ) { targetIsGridEnabled ->
        if (photos.isNotEmpty()) {
            if (targetIsGridEnabled) {
                LazyVerticalGrid(
                    modifier = modifier
                        .height(LocalConfiguration.current.screenHeightDp.dp / 2)
                        .fillMaxWidth(),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    items(photos) { photo ->
                        PhotoItem(photo)
                    }
                }
            } else {
                LazyColumn(
                    modifier = modifier
                        .height(LocalConfiguration.current.screenHeightDp.dp)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(photos) { photo ->
                        PhotoItem(photo)
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No photos found",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun PhotoItem(photo: Photo) {
    Box(
        modifier = Modifier
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = photo.url,
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
        )
    }
}