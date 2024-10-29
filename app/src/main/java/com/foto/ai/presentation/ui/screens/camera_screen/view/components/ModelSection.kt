package com.foto.ai.presentation.ui.screens.camera_screen.view.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.foto.ai.R
import com.foto.ai.domain.model.Model

@Composable
fun ModelSection(
    modifier: Modifier,
    models: List<Model> = emptyList(),
    onModelClick: (Model) -> Unit,
    selectedModel: Model? = null,
    isExpanded: Boolean,
    onChangeLayoutType: (Boolean) -> Unit
) {
    val angle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(128), label = ""
    )

    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .animateContentSize(tween(128))
        ) {
            Row {
                Text(
                    "Models",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onChangeLayoutType(!isExpanded) }) {
                    Icon(
                        painter = painterResource(
                            R.drawable.icon_arrow_drop_down,
                        ), contentDescription = null,
                        modifier = Modifier.rotate(angle)
                    )
                }
            }
            if (!isExpanded && selectedModel != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth().clickable {
                        onChangeLayoutType(true)
                    }
                ) {
                    AsyncImage(
                        model = selectedModel.avatarUrl,
                        modifier = Modifier
                            .clip(RoundedCornerShape(128.dp))
                            .height(64.dp)
                            .border(0.5.dp, MaterialTheme.colorScheme.primary, CircleShape),
                        contentScale = ContentScale.Fit,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        selectedModel.name,
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    if (models.isNotEmpty()) {
                        LazyVerticalGrid(
                            modifier = Modifier.height(288.dp),
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(items = models) { model ->
                                ModelItem(model, {
                                    onModelClick(model)
                                    onChangeLayoutType(false)
                                })
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(288.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No models found",
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.primary
                            )
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
                            onClick = {}
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
    }
}

@Composable
private fun ModelItem(model: Model, onModelClick: (Model) -> Unit) {
    val modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
    Box(
        modifier = modifier
            .clickable {
                onModelClick(model)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = model.avatarUrl,
                modifier = modifier
                    .clip(RoundedCornerShape(128.dp))
                    .border(0.5.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                model.name,
                modifier = Modifier,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}