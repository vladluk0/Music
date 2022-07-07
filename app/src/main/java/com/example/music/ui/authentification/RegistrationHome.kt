package com.example.music.ui.authentification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun RegistrationHome() {

    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        RegistrationNavigation(navController = navController)
    }
}