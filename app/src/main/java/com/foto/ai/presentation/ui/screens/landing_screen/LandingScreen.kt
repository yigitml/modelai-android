package com.foto.ai.presentation.ui.screens.landing_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foto.ai.R
import com.foto.ai.presentation.ui.components.ImagePager
import com.foto.ai.presentation.ui.components.ResourceImage
import com.foto.ai.presentation.ui.theme.AppTheme
import com.foto.ai.util.fadingEdge
import com.foto.ai.util.findActivity

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LandingScreen(modifier: Modifier, onToLoginScreenClick: () -> Unit) {
    var showImagePager by remember { mutableStateOf(false) }
    
    val images =
        listOf(
            R.drawable.photo,
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3,
            R.drawable.photo4,
            R.drawable.photo5,
            R.drawable.photo6,
            R.drawable.photo7,
            R.drawable.photo8,
        ).shuffled()
    var selectedImageIndex by remember { mutableIntStateOf(0) }

    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item { HeroSection(onToLoginScreenClick) }
            item { FeatureSection() }
            item {
                GallerySection(
                    images = images,
                    onImageClick = { index ->
                        selectedImageIndex = index
                        showImagePager = true
                    }
                )
            }
            item { CallToActionSection(onToLoginScreenClick) }
        }

        AnimatedVisibility(
            visible = showImagePager,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            ImagePager(
                images = images,
                initialIndex = selectedImageIndex,
                onDismiss = { showImagePager = false },
                windowSizeClass = calculateWindowSizeClass(LocalContext.current.findActivity())
            )
        }
    }
}


@Composable
fun HeroSection(onToLoginScreenClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(384.dp)
    ) {
        val heroImage = listOf(
            R.drawable.hero_image,
            R.drawable.hero_image1,
            R.drawable.hero_image2,
            R.drawable.hero_image3
        )[3]

        val surfaceColor = MaterialTheme.colorScheme.surface
        val topBottomFade = Brush.verticalGradient(
            0f to surfaceColor,
            0.1f to Color.Transparent,
            0.15f to Color.Transparent,
            1f to surfaceColor
        )
        ResourceImage(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f)
                .fadingEdge(topBottomFade),

            resourceId = heroImage,
            contentDescription = "Hero Image",
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp)
        ) {
            Text(
                "Hire an AI Photographer",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Take incredible photos of people with your personal AI Model",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = onToLoginScreenClick,
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
            ) {
                Text("Create your AI Model", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Spacer(Modifier.width(8.dp))
                Icon(
                    painter = painterResource(R.drawable.icon_add),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun FeatureSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(
            "Features",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(16.dp))
        FeatureItem(R.drawable.icon_camera, buildAnnotatedString {
            append("AI photos with ultra ")
            withStyle(
                style = SpanStyle(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Cyan,
                            Color.Blue
                        )
                    )
                )
            ) {
                append("photo-realistic")
            }
            append(" style")
        })
        FeatureItem(R.drawable.icon_auto_fix, buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Magenta,
                            Color.Red
                        )
                    )
                )
            ) {
                append("Fix and change")
            }
            append(" your photos")
        })
        FeatureItem(R.drawable.icon_photo_filter, buildAnnotatedString {
            append("Try ")
            withStyle(
                style = SpanStyle(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Yellow,
                            Color.Green
                        )
                    )
                )
            ) {
                append("clothes or makeup")
            }
            append(" ideas")
        })
    }
}

@Composable
fun FeatureItem(res: Int, text: AnnotatedString) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = res),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun GallerySection(images: List<Int>, onImageClick: (Int) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            "Gallery",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp),
            modifier = Modifier.height(216.dp)
        ) {
            itemsIndexed(images) { index, item ->
                ResourceImage(
                    resourceId = item,
                    contentDescription = "Sample Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(216.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onImageClick(index) }
                )
            }
        }
    }
}

@Composable
fun CallToActionSection(onToLoginScreenClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedButton(
            shape = MaterialTheme.shapes.medium,
            onClick = onToLoginScreenClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_fire),
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "Get Started Now",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLandingScreen() {
    AppTheme {
        LandingScreen(modifier = Modifier, onToLoginScreenClick = {})
    }
}