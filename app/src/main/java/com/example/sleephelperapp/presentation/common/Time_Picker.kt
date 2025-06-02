package com.example.sleephelperapp.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class SelectedTime(
    val hour: Int,
    val minute: Int,
    val formattedTime: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onTimeSelected: (SelectedTime) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Time") },
        text = {
            Column {
                TimeInput(
                    state = timePickerState,
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val selectedTime = SelectedTime(
                        hour = timePickerState.hour,
                        minute = timePickerState.minute,
                        formattedTime = formatTime(timePickerState.hour, timePickerState.minute)
                    )
                    onTimeSelected(selectedTime)
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

private fun formatTime(hour: Int, minute: Int): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(calendar.time)
}

@Composable
fun TimePicker() {
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf<SelectedTime?>(null) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        showTimePicker = true
        Spacer(modifier = Modifier.height(16.dp))

        selectedTime?.let { time ->
            Text("Selected Time: ${time.formattedTime}")
            Text("Hour: ${time.hour}, Minute: ${time.minute}")
        } ?: Text("No time selected")
    }

    if (showTimePicker) {
        TimePickerDialog(
            onTimeSelected = { time ->
                selectedTime = time
                showTimePicker = false
                println("Time selected: ${time.formattedTime}")
            },
            onDismiss = {
                showTimePicker = false
            }
        )
    }
}

/*@Composable
fun rememberTimePickerLauncher(
    onTimeSelected: (SelectedTime) -> Unit
): () -> Unit {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        TimePickerDialog(
            onTimeSelected = { time ->
                onTimeSelected(time)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

    return { showDialog = true }
}

@Composable
fun AnotherExampleWithLauncher() {
    var selectedTime by remember { mutableStateOf<SelectedTime?>(null) }
    val timePickerLauncher = rememberTimePickerLauncher { time ->
        selectedTime = time
        println("Time selected via launcher: ${time.formattedTime}")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = timePickerLauncher) {
            Text("Pick Time (Launcher Approach)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedTime?.let {
            Text("Selected: ${it.formattedTime}")
        }
    }
}*/

@Preview ()
@Composable
fun TimePickerExamplePreview() {
    TimePicker()
}