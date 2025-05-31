package com.example.sleephelperapp.presentation.screen.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sleephelperapp.R
import com.example.sleephelperapp.presentation.common.Top_Bar

@Composable
fun SleepSchedule(navigator: NavHostController) {
    SleepScheduleScreen(navigator = navigator, viewModel = hiltViewModel())
}

@Composable
fun SleepScheduleScreen(navigator: NavHostController, viewModel: SleepScheduleViewModel) {
    Scaffold(
        topBar = { Top_Bar(navigator, "Schedule") },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1C1B2A))
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            contentAlignment = Alignment.TopStart
        ) {
            SleepScreenContent(viewModel)
        }
    }
}

@Composable
fun SleepScreenContent(viewModel: SleepScheduleViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Wake Up Alarm Section
        CardSection {
            AlarmToggleRow(
                iconResId = R.drawable.outline_wb_sunny_24,
                title = "Wake up Alarm",
                time = "07:00 AM", // Placeholder time
                checked = viewModel.wakeUpAlarmEnabled,
                onCheckedChange = { viewModel.toggleWakeUpAlarm() }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        CardSection {
            AlarmToggleRow(
                iconResId = R.drawable.outline_moon_stars_24, // Moon and stars icon
                title = "Sleep time Alarm",
                time = "11:00 PM",
                checked = viewModel.sleepTimeAlarmEnabled,
                onCheckedChange = { viewModel.toggleSleepTimeAlarm() }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Scheduler Section
        CardSection {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Scheduler",
                    color = Color.White,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_timer_24), // Timer icon
                        contentDescription = "Sleep time duration",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Sleep time  11:00 PM - 07:00 AM",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Black and White Screen toggle
                SchedulerToggleRow(
                    iconResId = R.drawable.outline_eyeglasses_24, // Eyeglasses icon
                    text = "Black and White Screen",
                    checked = viewModel.blackAndWhiteScreenEnabled,
                    onCheckedChange = { viewModel.toggleBlackAndWhiteScreen() }
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Eye Comfort toggle
                SchedulerToggleRow(
                    iconResId = R.drawable.outline_eyeglasses_24, // Reusing eyeglasses icon, consider a different one if available
                    text = "Eye Comfort",
                    checked = viewModel.eyeComfortEnabled,
                    onCheckedChange = { viewModel.toggleEyeComfort() }
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Notification Off toggle
                SchedulerToggleRow(
                    iconResId = R.drawable.outline_notifications_off_24, // Notification off icon
                    text = "Notification Off",
                    checked = viewModel.notificationOffEnabled,
                    onCheckedChange = { viewModel.toggleNotificationOff() }
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Do Not Disturb toggle
                SchedulerToggleRow(
                    iconResId = R.drawable.outline_do_not_disturb_on_24, // Do not disturb icon
                    text = "Do not Disturb",
                    checked = viewModel.doNotDisturbEnabled,
                    onCheckedChange = { viewModel.toggleDoNotDisturb() }
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Flight Mode toggle
                SchedulerToggleRow(
                    iconResId = R.drawable.outline_flights_and_hotels_24, // Flight mode icon
                    text = "Flight Mode",
                    checked = viewModel.flightModeEnabled,
                    onCheckedChange = { viewModel.toggleFlightMode() }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun AlarmToggleRow(
    iconResId: Int,
    title: String,
    time: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = title,
            tint = Color.White,
            modifier = Modifier.size(28.dp) // Slightly larger icon
        )
        Spacer(modifier = Modifier.width(12.dp)) // Increased spacing
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge // Larger text for titles
            )
            Text(
                text = time,
                color = Color.White,
                fontSize = 22.sp, // Larger font for time
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary, // Use primary color from theme
                checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
        )
    }
}

/**
 * A reusable composable for a scheduler setting toggle row.
 * @param iconResId Resource ID for the leading icon.
 * @param text The text label for the setting.
 * @param checked The current checked state of the switch.
 * @param onCheckedChange Lambda to be invoked when the switch state changes.
 */
@Composable
fun SchedulerToggleRow(
    iconResId: Int,
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = text,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.weight(1f), // Occupy available space
            style = MaterialTheme.typography.bodyMedium
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
        )
    }
}

@Composable
fun CardSection(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Rounded corners
        color = Color(0xFF2A293D), // Slightly lighter dark background for the card
        shadowElevation = 4.dp // Add a subtle shadow
    ) {
        Column(modifier = Modifier.padding(16.dp)) { // Padding inside the card
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SleepScheduleScreenPreview() {
    val dummyNavigator = NavHostController(LocalContext.current)
    MaterialTheme {
        SleepSchedule(navigator = dummyNavigator)
    }
}
