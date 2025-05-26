package com.example.sleephelperapp.presentation.navigation

sealed class Graph(val route: String){
    data object Splash : Graph("splash")
    data object Register : Graph("register")
    data object Login : Graph("login")
    data object Home : Graph("home")
    data object Analysis : Graph("Analysis")
    data object Sound : Graph("Sound")
    data object Schedule : Graph("Schedule")
    data object Read : Graph("Read")
    data object Profile: Graph("Profile")
}
