package com.foto.ai.presentation.ui.screens

sealed class Screen (val route: String) {
    data object LandingScreen : Screen("landing_screen")
    data object LoginScreen : Screen("login_screen")
    data object OffersScreen : Screen("offers_screen")
    data object SubscriptionScreen : Screen("subscription_screen")
    data object CameraScreen : Screen("camera_screen")
}