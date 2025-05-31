package com.example.sleephelperapp.presentation.common

import android.widget.TimePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog

@Composable
fun TimePickerDialog(
    isDialogOpen: Boolean,
    onDismiss: () -> Unit,
    onTimeSelected: (Int, Int) -> Unit
) {
    if (isDialogOpen) {
        Dialog(onDismissRequest = { onDismiss() }) {
            AndroidView(
                factory = { context ->
                    TimePicker(context).apply {
                        setIs24HourView(true)
                    }
                },
                update = { timePicker ->
                    // Handle time selection
                    timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                        onTimeSelected(hourOfDay, minute)
                    }
                }
            )
        }
    }
}

@Composable
fun TimePickerExample() {
    var isDialogOpen by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { isDialogOpen = true }) {
            Text("Pick Time")
        }
        TimePickerDialog(
            isDialogOpen = isDialogOpen,
            onDismiss = { isDialogOpen = false },
            onTimeSelected = { hour, minute -> {/*Add variable to change*/}
                isDialogOpen = false
            }
        )
    }
}
@Composable
@Preview
fun TimePickerExamplePreview(){
    TimePickerExample()
}
