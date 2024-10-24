package com.foto.ai.presentation.ui.screens.camera_screen
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foto.ai.R
import com.foto.ai.presentation.ui.components.ResourceImage
import com.foto.ai.presentation.ui.components.ShimmerEffect
import com.foto.ai.presentation.ui.components.rememberFlowingRainbowBrush
import com.foto.ai.presentation.ui.theme.AppTheme

@Composable
fun CameraScreen(modifier: Modifier) {
    var isGridEnabled by remember { mutableStateOf(true) }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            item { ModelsSection(Modifier.fillMaxWidth()) }

            item {
                LayoutTypeSection(Modifier.fillMaxWidth(), isGridEnabled, onChangeIsGridEnabled = {
                    isGridEnabled = it
                })
            }

            item {
                LazyVerticalGrid(
                    modifier = Modifier.height(400.dp),
                    columns = GridCells.Fixed(if (isGridEnabled) 2 else 1),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(8) {
                        ImageItem()
                    }
                }
            }
        }
    }
}

@Composable
fun ModelsSection(modifier: Modifier) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                "Select Model",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                modifier = Modifier.height(244.dp),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(6) {
                    ModelItem()
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    onClick = {

                    }
                ) {
                    Text(
                        "Generate a model",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Composable
fun LayoutTypeSection(modifier: Modifier, isGridEnabled: Boolean, onChangeIsGridEnabled: (Boolean) -> Unit) {
    Box(modifier = modifier.padding(top = 16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "View Type",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Box{
                IconButton(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        onChangeIsGridEnabled(!isGridEnabled)
                    }
                ) {
                    if (isGridEnabled) {
                        Icon(
                            painterResource(R.drawable.icon_grid_view),
                            contentDescription = "Grid Icon"
                        )

                    } else {
                        Icon(
                            painterResource(R.drawable.icon_list_view),
                            contentDescription = "List Icon",
                        )
                    }
                }
                /*
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(text = { Text("Grid") }, onClick = {
                        selectedIndex = 0
                        expanded = false
                    })
                    DropdownMenuItem(text = { Text("List") }, onClick = {
                        selectedIndex = 1
                        expanded = false
                    })
                }

                 */
            }
        }
    }
}

@Composable
fun ModelItem() {
    val brush = rememberFlowingRainbowBrush()
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .size(48.dp)
            //.border(0.5.dp, brush, CircleShape)
            .border(0.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .clickable {

            },
        contentAlignment = Alignment.Center
    ) {
        ResourceImage(
            resourceId = R.drawable.photo6,
            modifier = Modifier.alpha(0.5f)
        )
    }
}

@Composable
fun ImageItem() {
    val cyberpunkColors = listOf(
        Color(0xFF00FFFF),  // Cyan
        Color(0xFFFF00FF), // Magenta
        Color(0xFF00FF00),  // Neon Green
        Color(0xFFFF1493),  // Deep Pink
        Color(0xFFFFFF00),  // Yellow
        Color(0xFF7F00FF)
    )
    val brush = rememberFlowingRainbowBrush(
        colors = cyberpunkColors,
        period = 10000f
    )
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(1.dp, brush, RectangleShape)
            //.border(1.dp, MaterialTheme.colorScheme.primary, RectangleShape)
            .clickable {

            },
        contentAlignment = Alignment.Center
    ) {
        /*
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Image Thumbnail",
            modifier = Modifier.size(64.dp)
        )

         */

        ShimmerEffect()
        //ResourceImage(resourceId = R.drawable.photo6, modifier= Modifier.alpha(0.5f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCameraScreen() {
    AppTheme {
        CameraScreen(Modifier)
    }
}