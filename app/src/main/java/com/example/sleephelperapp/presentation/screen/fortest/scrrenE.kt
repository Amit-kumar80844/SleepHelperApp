package com.example.sleephelperapp.presentation.screen.fortest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun screenE(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Screen C",
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold)
    }
}

