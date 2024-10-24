package com.foto.ai.presentation.ui.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun rememberFlowingRainbowBrush(
    colors: List<Color> = listOf(
        Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue, Color.Magenta
    ),
    period: Float = 5000f // Time in milliseconds for one complete cycle
): Brush {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = period.toInt(), easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    return remember(colors, phase) {
        val gradientColors = if (colors.size == 1) colors + colors else colors
        Brush.linearGradient(
            colors = gradientColors,
            start = Offset(
                x = cos(phase) * 100f,
                y = sin(phase) * 100f
            ),
            end = Offset(
                x = cos(phase + PI.toFloat()) * 100f,
                y = sin(phase + PI.toFloat()) * 100f
            ),
            tileMode = TileMode.Mirror
        )
    }
}