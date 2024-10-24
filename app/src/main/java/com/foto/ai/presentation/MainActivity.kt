package com.foto.ai.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.foto.ai.R
import com.foto.ai.presentation.ui.screens.Screen
import com.foto.ai.presentation.ui.screens.camera_screen.CameraScreen
import com.foto.ai.presentation.ui.screens.landing_screen.LandingScreen
import com.foto.ai.presentation.ui.screens.login_screen.LoginScreen
import com.foto.ai.presentation.ui.screens.offers_screen.OffersScreen
import com.foto.ai.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                var currentScreen by remember { mutableStateOf(Screen.LandingScreen.route) }

                fun navigate(route: String) {
                    if (route != currentScreen) {
                        navController.navigate(route) {
                            popUpTo(route) {
                                inclusive = true
                            }
                        }
                        currentScreen = route
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = buildAnnotatedString {
                                        append("Model")
                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colorScheme.primary,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        ) {
                                            append("AI")
                                        }
                                    }
                                )
                            },
                            actions = {
                                IconButton(
                                    modifier = Modifier.padding(4.dp),
                                    onClick = {
                                        navigate(Screen.CameraScreen.route)
                                    }) {
                                    Icon(
                                        painter = painterResource(R.drawable.icon_google),
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        when (currentScreen) {
                            Screen.CameraScreen.route -> {
                                BottomAppBar {
                                    IconButton(onClick = { navigate(Screen.LandingScreen.route) }) {
                                        Icon(
                                            painter = painterResource(R.drawable.icon_fire),
                                            contentDescription = "Fire Icon"
                                        )
                                    }
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LandingScreen.route
                    ) {
                        composable(route = Screen.LandingScreen.route) {
                            currentScreen = it.destination.route.toString()
                            LandingScreen(
                                modifier = Modifier.padding(innerPadding),
                                onToLoginScreenClick = {
                                    navigate(Screen.LoginScreen.route)
                                }
                            )
                        }

                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(
                                modifier = Modifier.padding(innerPadding),
                                onLoginClick = {
                                    navigate(Screen.OffersScreen.route)
                                }
                            )
                        }

                        composable(route = Screen.OffersScreen.route) {
                            OffersScreen(
                                modifier = Modifier.padding(innerPadding),
                                onSubscriptionClick = {
                                    // Handle subscription click
                                    // navController.navigate(Screen.SubscriptionScreen.route)
                                    navigate(Screen.CameraScreen.route)
                                }
                            )
                        }

                        composable(route = Screen.CameraScreen.route) {
                            CameraScreen(Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}