package com.foto.ai.presentation.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ImagePager(
    images: List<Int>,
    initialIndex: Int,
    onDismiss: () -> Unit,
    windowSizeClass : WindowSizeClass
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = initialIndex,
        pageCount = { images.size }
    )

    var cardBounds by remember { mutableStateOf(Rect.Zero) }
    var leftButtonBounds by remember { mutableStateOf(Rect.Zero) }
    var rightButtonBounds by remember { mutableStateOf(Rect.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        if (!cardBounds.contains(it) && !leftButtonBounds.contains(it) && !rightButtonBounds.contains(
                                it
                            )
                        ) {
                            onDismiss()
                        }
                    }
                )
            }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth(
                    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) 0.8f else 0.5f
                )
                .aspectRatio(1f)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(8.dp))
                .onGloballyPositioned {
                    cardBounds = it.boundsInParent()
                }
        ) { page ->
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
            ) {
                ResourceImage(
                    resourceId = images[page],
                    contentDescription = "Enlarged Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    if (pagerState.currentPage > 0) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                enabled = pagerState.currentPage > 0,
                modifier = Modifier.onGloballyPositioned {
                    leftButtonBounds = it.boundsInParent()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Previous Image",
                    modifier = Modifier.size(32.dp)
                )
            }
            IconButton(
                onClick = {
                    if (pagerState.currentPage < images.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                enabled = pagerState.currentPage < images.size - 1,
                modifier = Modifier.onGloballyPositioned {
                    rightButtonBounds = it.boundsInParent()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next Image",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}