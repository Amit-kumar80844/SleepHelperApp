package com.example.sleephelperapp.presentation.screen.schedule

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sleephelperapp.R
import com.example.sleephelperapp.presentation.common.SchedulerToggleRow
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
            .background(topBarColor)
    ) {
        Text(
            text = "Schedule",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFF000000))
                .padding(horizontal = 8.dp),
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
                onTimeClick = { viewModel.showWakeUpTimePicker()
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ScheduleTimePicker(
                            label = "Start Time",
                            time = viewModel.sleepTimeSchedule.value,
                            onTimeClick = {viewModel.showSleepTimeSchedule()}
                        )
                        ScheduleTimePicker(
                            label = "End Time",
                            time = viewModel.wakeTimeSchedule.value,
                            onTimeClick = {viewModel.showWakeTimeSchedule() }
                        )
                    }
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
    if (viewModel.showSleepTimeSchedule.value) {
        TimePickerDialog(
            onTimeSelected = { selectedTime ->
                viewModel.updateSleepTimeSchedule(selectedTime.formattedTime)
                viewModel.hideSleepTimeSchedule()
            },
            onDismiss = {viewModel.hideSleepTimeSchedule() })
    }
    if(viewModel.showWakeTimeSchedule.value){
        TimePickerDialog(
            onTimeSelected = {
                selectedTime -> viewModel.updateWakeTimeSchedule(selectedTime.formattedTime)
                viewModel.hideWakeTimeSchedule()
            },
            onDismiss = { viewModel.hideWakeTimeSchedule()}
        )
    }
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
    onTimeClick: () -> Unit
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
                .clickable { onTimeClick() }
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
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
        )
    }
}

@Composable
fun ScheduleTimePicker(
    modifier: Modifier = Modifier,
    label: String,
    time: String,
    onTimeClick: () -> Unit
) {
    Log.d("ScheduleTimePicker", "Time: $time")
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTimeClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                color = Color.White,
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = time,
                color = Color.White,
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineSmall
            )
        }
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
