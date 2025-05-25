package com.example.sleephelperapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sleephelperapp.presentation.common.SplashScreen
import com.example.sleephelperapp.presentation.screen.Authentfication.Login
import com.example.sleephelperapp.presentation.screen.Authentication.Registration
import com.example.sleephelperapp.presentation.screen.fortest.screenA
import com.example.sleephelperapp.presentation.screen.fortest.screenB
import com.example.sleephelperapp.presentation.screen.fortest.screenC
import com.example.sleephelperapp.presentation.screen.fortest.screenD
import com.example.sleephelperapp.presentation.screen.fortest.screenE
import com.example.sleephelperapp.presentation.screen.home.HomeScreen

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
        composable(Graph.Home.route){
            HomeScreen(navController)
        }
        composable(Graph.Analysis.route){
            screenA(navController)
        }
        composable(Graph.Read.route){
            screenB(navController)
        }
        composable(Graph.Schedule.route){
            screenC(navController)
        }
        composable(Graph.Sound.route){
            screenD(navController)
        }
        composable(Graph.Schedule.route){
            screenE(navController)
        }
    }
}