package com.example.sleephelperapp.presentation.screen.schedule

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleephelperapp.data.model.SleepScheduleEntity
import com.example.sleephelperapp.domain.repository.AlarmScheduler
import com.example.sleephelperapp.domain.usecase.schedule.GetSleepScheduleUseCase
import com.example.sleephelperapp.domain.usecase.schedule.SetSleepScheduleUseCase
import com.example.sleephelperapp.domain.usecase.schedule.ToggleSleepSettingUseCase
import com.example.sleephelperapp.domain.usecase.schedule.UpdateSleepTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepScheduleViewModel @Inject constructor(
    private val toggleSleepSettingUseCase: ToggleSleepSettingUseCase,
    private val getSleepScheduleUseCase: GetSleepScheduleUseCase,
    private val setSleepScheduleUseCase: SetSleepScheduleUseCase,
    private val updateSleepTimeUseCase : UpdateSleepTimeUseCase,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {
    private val _schedule = MutableStateFlow<SleepScheduleEntity?>(null)
    val schedule: StateFlow<SleepScheduleEntity?> = _schedule.asStateFlow()
    var wakeUpAlarmEnabled by mutableStateOf(false)
    var sleepTimeAlarmEnabled by mutableStateOf(false)
    var blackAndWhiteScreenEnabled by mutableStateOf(false)
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

    private val _sleepTimeSchedule = mutableStateOf("07:00 PM")
    val sleepTimeSchedule: State<String> = _sleepTimeSchedule

    private val _wakeTimeSchedule = mutableStateOf("11:00 AM")
    val wakeTimeSchedule: State<String> = _wakeTimeSchedule

    private val _showSleepTimeSchedule = mutableStateOf(false)
    val showSleepTimeSchedule: State<Boolean> = _showSleepTimeSchedule

    private val _showWakeTimeSchedule = mutableStateOf(false)
    val showWakeTimeSchedule: State<Boolean> = _showWakeTimeSchedule

    init {
      /*  println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")*/
        ensureDefaultSchedule()
        observeSleepSchedule()
    }
    private fun ensureDefaultSchedule() {
        viewModelScope.launch {
            val current = getSleepScheduleUseCase().first()
            if (current == null) {
                val default = SleepScheduleEntity()
                setSleepScheduleUseCase(default)
                Log.d("SleepScheduleViewModel", "Default schedule inserted")
            }
        }
    }
    /**
     * first default then collect from room
     *
     * better code by init in viewmodel
     * */
    private fun observeSleepSchedule() {
        viewModelScope.launch {
            Log.d("SleepScheduleViewModel", "Schedule: $schedule  pahle")
            getSleepScheduleUseCase().collect { sleepSchedule ->
                _schedule.value = sleepSchedule
                sleepSchedule?.let { schedule ->
                    Log.d("SleepScheduleViewModel", "Schedule: $schedule badme ")
                    // Time field
                    _wakeUpTime.value = schedule.wakeUpTime
                    _sleepTime.value = schedule.sleepTime
                    _sleepTimeSchedule.value = schedule.sleepTimeSchedule
                    _wakeTimeSchedule.value = schedule.wakeTimeSchedule
                    // Toggle setting
                    wakeUpAlarmEnabled = schedule.wakeUpEnabled
                    sleepTimeAlarmEnabled = schedule.sleepAlarmEnabled
                    blackAndWhiteScreenEnabled = schedule.blackAndWhiteEnabled
                    eyeComfortEnabled = schedule.eyeComfortEnabled
                    notificationOffEnabled = schedule.notificationOffEnabled
                    doNotDisturbEnabled = schedule.doNotDisturbEnabled
                    flightModeEnabled = schedule.flightModeEnabled
                }
            }
        }
    }
/**         Initialization end                */

    /**
     * Update the sleepTime in the database
     * @param key The key of the toggle setting to update
     * @param newTime The new value of the toggle setting
     * */
private fun updateSleepTime(key: String, newTime: String) {
    viewModelScope.launch {
        val current = getSleepScheduleUseCase().first()
        if (current != null) {
            updateSleepTimeUseCase(key, newTime, current)
        }
    }
}
    /**
     * Update the toggle setting in the database
     * @param key The key of the toggle setting to update
     * @param newValue The new value of the toggle setting
     * */
    private fun updateToggleSleepSetting(key: String, newValue: Boolean) {
        viewModelScope.launch {
            val current = getSleepScheduleUseCase().first()
            if(current != null){
                toggleSleepSettingUseCase(key, newValue, current)
            }
        }
    }

    fun updateSleepTimeSchedule(time: String){
        _sleepTimeSchedule.value =time
        updateSleepTime("sleepTimeSchedule",time)
    }
    fun updateWakeTimeSchedule(time: String){
        _wakeTimeSchedule.value =time
        updateSleepTime("wakeTimeSchedule",time)
    }
    /**
     * both show is used for time picker
     * */
    fun showSleepTimeSchedule(){
        _showSleepTimeSchedule.value = true
        Log.d("SleepScheduleViewModel", "showSleepTimeSchedule called")
    }
    fun hideSleepTimeSchedule(){
        _showSleepTimeSchedule.value = false
    }
    fun showWakeTimeSchedule(){
        _showWakeTimeSchedule.value = true
    }
    fun hideWakeTimeSchedule(){
        _showWakeTimeSchedule.value = false
    }
    fun updateWakeUpTime(time: String) {
        _wakeUpTime.value = time
        updateSleepTime("wakeUpTime",time)
    }
    fun updateSleepTime(time: String) {
        _sleepTime.value = time
        updateSleepTime("sleepTime",time)
    }
    /**
     * picker  do not have @param*/
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
        updateToggleSleepSetting("wakeUp", wakeUpAlarmEnabled)
        if(wakeUpAlarmEnabled){
            alarmScheduler.scheduleWakeUpAlarm(wakeUpTime.value)
        }else{
            alarmScheduler.cancelWakeUpAlarm()
        }
    }
    fun toggleSleepTimeAlarm() {
        sleepTimeAlarmEnabled = !sleepTimeAlarmEnabled
        updateToggleSleepSetting("sleepAlarm", sleepTimeAlarmEnabled)
        if(sleepTimeAlarmEnabled){
            alarmScheduler.scheduleSleepAlarm(sleepTime.value)
        }else{
            alarmScheduler.cancelSleepAlarm()
        }
    }
    fun toggleBlackAndWhiteScreen() {
        blackAndWhiteScreenEnabled = !blackAndWhiteScreenEnabled
        updateToggleSleepSetting("bwScreen", blackAndWhiteScreenEnabled)
    }
    fun toggleEyeComfort() {
        eyeComfortEnabled = !eyeComfortEnabled
        updateToggleSleepSetting("eyeComfort", eyeComfortEnabled)
    }
    fun toggleNotificationOff() {
        notificationOffEnabled = !notificationOffEnabled
        updateToggleSleepSetting("notificationOff", notificationOffEnabled)
    }
    fun toggleDoNotDisturb() {
        doNotDisturbEnabled = !doNotDisturbEnabled
        updateToggleSleepSetting("doNotDisturb", doNotDisturbEnabled)
    }
    fun toggleFlightMode() {
        flightModeEnabled = !flightModeEnabled
        updateToggleSleepSetting("flightMode", flightModeEnabled)
    }
}