package com.example.sleephelperapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SleepScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1B2A))
            .padding(16.dp)
    ) {
        // Wake Up Section
        CardSection {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Wake up",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Wake up", color = Color.White)
                    Text("7:00 AM", color = Color.White, fontSize = 20.sp)
                }
                Switch(
                    checked = true,
                    onCheckedChange = {},
                    colors = SwitchDefaults.colors(checkedThumbColor = Color.Green)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Custom alarm",
                color = Color.Gray,
                modifier = Modifier.clickable { /* Navigate */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Scheduler Section
        CardSection {
            Text("Scheduler", color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Scheduler",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Sleep time  11:00 PM - 7:00 AM", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Blue light",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Blue light", color = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                Switch(checked = false, onCheckedChange = {})
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Wake up",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Wake up", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notification Block Section
        CardSection {
            Text("Notification block", color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Sleep time", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Do Not Disturb
        CardSection {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Do not disturb",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Do not disturb", color = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                Switch(checked = true, onCheckedChange = {})
            }
        }
    }
}

@Composable
fun CardSection(content: @Composable ColumnScope.() -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2B40)),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}


@Composable
@Preview(showBackground = true , backgroundColor = android.graphics.Color.WHITE.toLong())
fun CardPreview(){
    SleepScreen()
}