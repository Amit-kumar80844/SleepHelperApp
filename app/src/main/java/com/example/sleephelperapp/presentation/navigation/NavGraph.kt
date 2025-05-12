package com.example.sleephelperapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sleephelperapp.presentation.common.SplashScreen

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Graph.Splash.route){
        composable(Graph.Splash.route){
            SplashScreen(navController)
        }
//        more can be added
    }
}