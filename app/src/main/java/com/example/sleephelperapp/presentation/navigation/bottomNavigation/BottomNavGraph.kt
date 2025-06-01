package com.example.sleephelperapp.presentation.navigation.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sleephelperapp.presentation.navigation.Graph
import com.example.sleephelperapp.presentation.screen.fortest.screenA
import com.example.sleephelperapp.presentation.screen.fortest.screenB
import com.example.sleephelperapp.presentation.screen.fortest.screenC
import com.example.sleephelperapp.presentation.screen.fortest.screenD
import com.example.sleephelperapp.presentation.screen.fortest.screenE
import com.example.sleephelperapp.presentation.screen.schedule.SleepSchedule

@Composable
fun BottomNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Graph.Analysis.route,
        modifier = modifier
    ) {
        composable(Graph.Analysis.route) {
            SleepSchedule(navController)
        }
        composable(Graph.Read.route) {
            screenB(navController)
        }
        composable(Graph.Schedule.route) {
            screenC(navController)
        }
        composable(Graph.Sound.route) {
            screenD(navController)
        }
        composable(Graph.Profile.route) {
            screenE(navController)
        }
    }
}
