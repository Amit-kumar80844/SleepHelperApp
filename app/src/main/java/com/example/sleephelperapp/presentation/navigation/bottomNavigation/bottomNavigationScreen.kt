package com.example.sleephelperapp.presentation.navigation.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class bottomNavigationScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    data object Analysis : bottomNavigationScreen(
        route = "Analysis",
        title = "analysis",
        icon = Icons.Default.Favorite
    )
    data object Sound: bottomNavigationScreen(
        route = "Sound",
        title = "sound",
        icon = Icons.Default.PlayArrow
    )
    data object Schedule : bottomNavigationScreen(
        route = "Schedule",
        title = "schedule",
        icon = Icons.Default.Settings
    )
    data object Read : bottomNavigationScreen(
        route = "Read",
        title = "read",
        icon = Icons.Default.Create
    )
    data object Profile : bottomNavigationScreen(
        route = "Profile",
        title = "profile",
        icon =Icons.Default.AccountCircle
    )
}