package com.example.sleephelperapp.presentation.screen.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleephelperapp.data.model.SleepScheduleEntity
import com.example.sleephelperapp.domain.usecase.schedule.GetSleepScheduleUseCase
import com.example.sleephelperapp.domain.usecase.schedule.SetSleepScheduleUseCase
import com.example.sleephelperapp.domain.usecase.schedule.ToggleSleepSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepScheduleViewModel @Inject constructor(
    private val toggleSleepSettingUseCase: ToggleSleepSettingUseCase,
    private val getSleepScheduleUseCase: GetSleepScheduleUseCase,
    private val setSleepScheduleUseCase: SetSleepScheduleUseCase
) : ViewModel() {
    var wakeUpAlarmEnabled by mutableStateOf(false)
    var sleepTimeAlarmEnabled by mutableStateOf(true)
    var blackAndWhiteScreenEnabled by mutableStateOf(true)
    var eyeComfortEnabled by mutableStateOf(false)
    var notificationOffEnabled by mutableStateOf(false)
    var doNotDisturbEnabled by mutableStateOf(false)
    var flightModeEnabled by mutableStateOf(false)

    private val _wakeUpTime = mutableStateOf("07:00 AM")
    val wakeUpTime: State<String> = _wakeUpTime

    private val _sleepTime = mutableStateOf("11:00 PM")
    val sleepTime: State<String> = _sleepTime

    private val _showWakeUpTimePicker = mutableStateOf(false)
    val showWakeUpTimePicker: State<Boolean> = _showWakeUpTimePicker

    private val _showSleepTimePicker = mutableStateOf(false)
    val showSleepTimePicker: State<Boolean> = _showSleepTimePicker

    fun updateWakeUpTime(time: String) {
        _wakeUpTime.value = time
    }

    fun updateSleepTime(time: String) {
        _sleepTime.value = time
    }

    fun showWakeUpTimePicker() {
        _showWakeUpTimePicker.value = true
    }

    fun hideWakeUpTimePicker() {
        _showWakeUpTimePicker.value = false
    }

    fun showSleepTimePicker() {
        _showSleepTimePicker.value = true
    }

    fun hideSleepTimePicker() {
        _showSleepTimePicker.value = false
    }
    
    fun toggleWakeUpAlarm() {
        wakeUpAlarmEnabled = !wakeUpAlarmEnabled
    }
    fun toggleSleepTimeAlarm() {
        sleepTimeAlarmEnabled = !sleepTimeAlarmEnabled
    }
    fun toggleBlackAndWhiteScreen() {
        blackAndWhiteScreenEnabled = !blackAndWhiteScreenEnabled
    }
    fun toggleEyeComfort() {
        eyeComfortEnabled = !eyeComfortEnabled
    }
    fun toggleNotificationOff() {
        notificationOffEnabled = !notificationOffEnabled
    }
    fun toggleDoNotDisturb() {
        doNotDisturbEnabled = !doNotDisturbEnabled
    }
    fun toggleFlightMode() {
        flightModeEnabled = !flightModeEnabled
    }
}