package com.example.sleephelperapp.presentation.common


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
private const val SPLASH_SCREEN_DURATION_MILLIS = 2000L
@Composable
fun SplashScreen(navController: NavHostController) {
    // Display the splash screen
    SplashDisplay()
    LaunchedEffect(Unit) {
        delay(SPLASH_SCREEN_DURATION_MILLIS)
    }
}
@Composable
fun SplashDisplay() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF03030d)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProgressBar_Splash(modifier = Modifier.size(200.dp))
        Text(
            text = "Sleep Helper",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(0.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SplashDisplay()
}

