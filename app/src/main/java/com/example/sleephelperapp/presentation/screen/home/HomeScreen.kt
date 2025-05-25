package com.example.sleephelperapp.presentation.screen.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sleephelperapp.R
import com.example.sleephelperapp.presentation.navigation.bottomNavigation.BottomNavItem
import com.example.sleephelperapp.presentation.navigation.bottomNavigation.bottomNavigationScreen
import com.example.sleephelperapp.presentation.theme.bottomBarColor

@Composable
fun HomeScreen(navController: NavHostController) {
    BottomNavigationBar(navController = navController)
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavItem(
            screen = bottomNavigationScreen.Analysis,
            icon = R.drawable.reporticon,
            label = "Analysis"
        ),
        BottomNavItem(
            screen = bottomNavigationScreen.Sound,
            icon = R.drawable.reporticon,
            label = "Sound"
        ),
        BottomNavItem(
            screen = bottomNavigationScreen.Schedule,
            icon = R.drawable.reporticon,
            label = "Schedule"
        ),
        BottomNavItem(
            screen = bottomNavigationScreen.Read,
            icon = R.drawable.reporticon,
            label = "Read"
        ),
        BottomNavItem(
            screen = bottomNavigationScreen.Profile,
            icon = R.drawable.reporticon,
            label = "Profile"
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

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
                painter = painterResource(id = screen.screen.icon),
                contentDescription = screen.label
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
