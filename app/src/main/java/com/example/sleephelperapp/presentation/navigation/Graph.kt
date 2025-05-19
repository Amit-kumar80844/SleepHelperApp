package com.example.sleephelperapp.presentation.navigation

sealed class Graph(val route: String){
    data object Splash : Graph("splash")
    data object Login : Graph("login")
    data object Home : Graph("home")
    data object Register : Graph("register")
    data object Profile : Graph("profile")
}
