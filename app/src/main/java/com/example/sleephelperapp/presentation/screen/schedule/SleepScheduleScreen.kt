package com.example.sleephelperapp.presentation.screen.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sleephelperapp.R
import com.example.sleephelperapp.presentation.common.TimePickerDialog
import com.example.sleephelperapp.presentation.theme.topBarColor

@Composable
fun SleepSchedule(navigator: NavHostController) {
    SleepScheduleScreen(navigator = navigator, viewModel = hiltViewModel())
}

@Composable
fun SleepScheduleScreen(navigator: NavHostController, viewModel: SleepScheduleViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(topBarColor.value))
    ) {
        Text(
            text = "Schedule",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFF000000))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            SleepScreen(viewModel)
        }
    }
}

@Composable
fun SleepScreen(viewModel: SleepScheduleViewModel) {
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
                time = viewModel.wakeUpTime.value,
                checked = viewModel.wakeUpAlarmEnabled,
                onCheckedChange = { viewModel.toggleWakeUpAlarm() },
                onTimeClick = {
                    viewModel.showWakeUpTimePicker()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CardSection {
            AlarmToggleRow(
                iconResId = R.drawable.outline_moon_stars_24,
                title = "Sleep time Alarm",
                time = viewModel.sleepTime.value,
                checked = viewModel.sleepTimeAlarmEnabled,
                onCheckedChange = { viewModel.toggleSleepTimeAlarm() },
                onTimeClick = {
                     viewModel.showSleepTimePicker()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                        painter = painterResource(id = R.drawable.outline_timer_24),
                        contentDescription = "Sleep time duration",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Sleep time  ${viewModel.sleepTime.value} - ${viewModel.wakeUpTime.value}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Scheduler toggles remain the same
                SchedulerToggleRow(
                    iconResId = R.drawable.outline_eyeglasses_24,
                    text = "Black and White Screen",
                    checked = viewModel.blackAndWhiteScreenEnabled,
                    onCheckedChange = { viewModel.toggleBlackAndWhiteScreen() }
                )
                Spacer(modifier = Modifier.height(8.dp))

                SchedulerToggleRow(
                    iconResId = R.drawable.outline_eyeglasses_24,
                    text = "Eye Comfort",
                    checked = viewModel.eyeComfortEnabled,
                    onCheckedChange = { viewModel.toggleEyeComfort() }
                )
                Spacer(modifier = Modifier.height(8.dp))

                SchedulerToggleRow(
                    iconResId = R.drawable.outline_notifications_off_24,
                    text = "Notification Off",
                    checked = viewModel.notificationOffEnabled,
                    onCheckedChange = { viewModel.toggleNotificationOff() }
                )
                Spacer(modifier = Modifier.height(8.dp))

                SchedulerToggleRow(
                    iconResId = R.drawable.outline_do_not_disturb_on_24,
                    text = "Do not Disturb",
                    checked = viewModel.doNotDisturbEnabled,
                    onCheckedChange = { viewModel.toggleDoNotDisturb() }
                )
                Spacer(modifier = Modifier.height(8.dp))

                SchedulerToggleRow(
                    iconResId = R.drawable.outline_flights_and_hotels_24,
                    text = "Flight Mode",
                    checked = viewModel.flightModeEnabled,
                    onCheckedChange = { viewModel.toggleFlightMode() }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

    // Time Picker Dialogs
    if (viewModel.showWakeUpTimePicker.value) {
        TimePickerDialog(
            onTimeSelected = { selectedTime ->
                viewModel.updateWakeUpTime(selectedTime.formattedTime)
                viewModel.hideWakeUpTimePicker()
            },
            onDismiss = {
                viewModel.hideWakeUpTimePicker()
            }
        )
    }

    if (viewModel.showSleepTimePicker.value) {
        TimePickerDialog(
            onTimeSelected = { selectedTime ->
                viewModel.updateSleepTime(selectedTime.formattedTime)
                viewModel.hideSleepTimePicker()
           },
            onDismiss = {
                viewModel.hideSleepTimePicker()
            }
        )
    }
}

@Composable
fun AlarmToggleRow(
    iconResId: Int,
    title: String,
    time: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTimeClick: () -> Unit // New parameter for time click
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = title,
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { onTimeClick() } // Make time clickable
        ) {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = time,
                color = Color.White,
                fontSize = 22.sp,
                style = MaterialTheme.typography.headlineSmall
            )
        }
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
            modifier = Modifier.weight(1f),
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
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFF2A293D),
        shadowElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}
