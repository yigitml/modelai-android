package com.foto.ai.presentation.ui.screens.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foto.ai.R
import com.foto.ai.presentation.ui.components.ResourceImage
import com.foto.ai.presentation.ui.theme.AppTheme

@Composable
fun LoginScreen(modifier: Modifier, onLoginClick: () -> Unit) {
    Surface(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                OutlinedButton(
                    shape = MaterialTheme.shapes.medium,
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    ResourceImage(
                        resourceId = R.drawable.icon_google,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Sign In With Google",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    AppTheme {
        LoginScreen(modifier = Modifier, onLoginClick = {})
    }
}