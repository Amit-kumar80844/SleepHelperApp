package com.example.sleephelperapp.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.sleephelperapp.presentation.theme.topBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top_Bar(navigator: NavHostController , text:String){
    TopAppBar(
        title = { Text(text, color = Color.White) },
        navigationIcon = {
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
@Preview(showBackground = true)
@Composable
fun MediumTopAppBarPreview() {
   Top_Bar(navigator = rememberNavController(), text = "Preview Title")
}