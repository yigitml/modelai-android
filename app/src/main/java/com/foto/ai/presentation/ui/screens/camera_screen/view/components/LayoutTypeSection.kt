package com.foto.ai.presentation.ui.screens.camera_screen.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.foto.ai.R

@Composable
fun LayoutTypeSection(
    modifier: Modifier,
    isGridEnabled: Boolean,
    onChangeLayoutType: (Boolean) -> Unit
) {
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
                        onChangeLayoutType(!isGridEnabled)
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