package com.example.sleephelperapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sleephelperapp.presentation.theme.topBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top_Bar(navigator: NavHostController , text:String){
    TopAppBar(
        title = { Text(text, color = Color.White) },
        navigationIcon = {
            // Add a back button if appropriate for your navigation flow
            // For example, if this screen is not the start destination
            if (navigator.previousBackStackEntry != null) {
                IconButton(onClick = { navigator.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topBarColor.copy(alpha = 0.8f)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBarExample(
    onOpenMenu: () -> Unit = { /* Default empty action */ },
    text:String = "Medium Top App Bar",
    navigator: NavHostController,

) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =topBarColor.copy(alpha = 0.8f) ,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = text,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    if (navigator.previousBackStackEntry != null){
                        IconButton(onClick ={
                            { navigator.popBackStack() }
                        } ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Navigate back"
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = onOpenMenu) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Open menu"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        ScrollContent(innerPadding = innerPadding)
    }
}

@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    LazyColumn(
        contentPadding = innerPadding, // Apply the innerPadding from Scaffold
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp) // Optional: Add horizontal padding to items
    ) {
        items(19) { index ->
            Text(
                text = "Item #$index",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp) // Add some padding to each item
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediumTopAppBarPreview() {
    // You would wrap this in your app's theme for accurate preview
    // YourAppTheme {
/*
    MediumTopAppBarExample(navigator = NavHostController())
*/
    // }
}