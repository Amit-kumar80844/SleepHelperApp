package com.example.sleephelperapp.presentation.screen.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sleephelperapp.R
import com.example.sleephelperapp.presentation.navigation.Graph
import com.example.sleephelperapp.presentation.navigation.NavGraph
import com.example.sleephelperapp.presentation.navigation.bottomNavigation.BottomNavGraph
import com.example.sleephelperapp.presentation.navigation.bottomNavigation.BottomNavItem
import com.example.sleephelperapp.presentation.navigation.bottomNavigation.bottomNavigationScreen
import com.example.sleephelperapp.presentation.theme.bottomBarColor

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        bottomBar = {
            if (currentDestination?.route in listOf(
                    Graph.Analysis.route,
                    Graph.Read.route,
                    Graph.Schedule.route,
                    Graph.Sound.route,
                    Graph.Profile.route
                )
            ) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        BottomNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val screens = listOf(
        BottomNavItem(
            screen = bottomNavigationScreen.Analysis,
            icon = Icons.Default.Favorite,
            label = "Analysis"
        ),
        BottomNavItem(
            screen = bottomNavigationScreen.Sound,
            icon = Icons.Default.PlayArrow,
            label = "Sound"
        ),
        BottomNavItem(
            screen = bottomNavigationScreen.Schedule,
            icon = Icons.Default.Settings,
            label = "Schedule"
        ),
        BottomNavItem(
            screen = bottomNavigationScreen.Read,
            icon = Icons.Default.Create,
            label = "Read"
        ),
        BottomNavItem(
            screen = bottomNavigationScreen.Profile,
            icon = Icons.Default.AccountCircle,
            label = "Profile"
        )
    )

    BottomAppBar(
        containerColor = bottomBarColor.copy(alpha = 0.8f),
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navController = navController,
                currentDestination = currentDestination
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    val selected = currentDestination?.route == screen.screen.route
    NavigationBarItem(
        label = {
            Text(text = screen.label)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.label,
                tint = Color.Black
            )
        },
        selected = selected,
        onClick = {
            navController.navigate(screen.screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}
