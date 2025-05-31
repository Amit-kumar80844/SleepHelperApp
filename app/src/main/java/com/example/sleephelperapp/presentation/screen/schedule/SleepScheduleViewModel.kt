package com.example.sleephelperapp.presentation.screen.schedule

import androidx.compose.runtime.getValue
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
    private val _sleepSchedule = MutableStateFlow<SleepScheduleEntity?>(null)
    val sleepSchedule: StateFlow<SleepScheduleEntity?> = _sleepSchedule.asStateFlow()
    
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