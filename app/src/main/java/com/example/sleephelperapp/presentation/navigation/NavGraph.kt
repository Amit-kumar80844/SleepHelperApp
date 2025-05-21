package com.example.sleephelperapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sleephelperapp.presentation.common.SplashScreen
import com.example.sleephelperapp.presentation.screen.Authentfication.Login
import com.example.sleephelperapp.presentation.screen.Authentication.Registration

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Graph.Splash.route){
        composable(Graph.Splash.route){
            SplashScreen(navController)
        }
        composable(Graph.Register.route) {
            Registration(navController)
        }
        composable(Graph.Login.route) {
            Login(navController)
        }

//        more can be added
    }
}