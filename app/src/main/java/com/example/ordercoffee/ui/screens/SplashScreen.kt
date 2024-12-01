package com.example.ordercoffee.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.ordercoffee.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Image(
        painter = painterResource(id = R.drawable.splash_img),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // Ensure the image fills the entire screen
    )
    LaunchedEffect(key1 = true) {
        delay(2000L) // Wait for 2 seconds
        navController.navigate("home_screen") {
            popUpTo("splash_screen") { inclusive = true }
        }
    }
}

