package com.example.sleephelperapp.presentation.screen.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sleephelperapp.domain.model.SleepSchedule
import com.example.sleephelperapp.presentation.common.SleepScreen
import com.example.sleephelperapp.presentation.common.Top_Bar
import com.example.sleephelperapp.presentation.theme.topBarColor

@Composable
fun SleepSchedule(navigator:NavHostController){

}

@Composable
fun SleepScheduleScreen(navigator: NavHostController){
    Scaffold(
        topBar = { Top_Bar(navigator, "Schedule") },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ){
            SleepScreen()
    }
  }
}
@Preview
@Composable
fun SleepScheduleScreenPreview(){
    SleepScheduleScreen(navigator = NavHostController(LocalContext.current))
}