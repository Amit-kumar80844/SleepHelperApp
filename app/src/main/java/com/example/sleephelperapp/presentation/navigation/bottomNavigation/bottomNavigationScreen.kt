package com.example.sleephelperapp.presentation.navigation.bottomNavigation

import com.example.sleephelperapp.R

sealed class bottomNavigationScreen(
    val route: String,
    val title: String,
    val icon: Int
){
    object Analysis : bottomNavigationScreen(
        route = "Analysis",
        title = "analysis",
        icon = R.drawable.reporticon
    )
    object Sound: bottomNavigationScreen(
        route = "Sound",
        title = "sound",
        icon = R.drawable.relaxsoundicon
    )
    object Schedule : bottomNavigationScreen(
        route = "Schedule",
        title = "schedule",
        icon = R.drawable.scheduleimage
    )
    object Read : bottomNavigationScreen(
        route = "Read",
        title = "read",
        icon = R.drawable.storiesicon
    )
    object Profile : bottomNavigationScreen(
        route = "Profile",
        title = "profile",
        icon = R.drawable.profileicon
    )
}