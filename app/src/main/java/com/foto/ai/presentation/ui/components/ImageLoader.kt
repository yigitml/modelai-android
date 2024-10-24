package com.foto.ai.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NetworkImage(modifier: Modifier = Modifier, url: String, contentScale: ContentScale, contentDescription: String? = null) {
    AsyncImage(
        model = url,
        contentScale = contentScale,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun ResourceImage(
    modifier: Modifier = Modifier,
    resourceId: Int,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(resourceId)
            .build(),
        contentScale = contentScale,
        contentDescription = contentDescription,
        modifier = modifier
    )
}