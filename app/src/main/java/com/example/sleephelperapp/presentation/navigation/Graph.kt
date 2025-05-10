package com.example.sleephelperapp.presentation.navigation

sealed class Graph(val route: String){
    object Splash : Graph("splash")
    object Login : Graph("login")
    object Home : Graph("home")
    object Register : Graph("register")
    object Profile : Graph("profile")
}
