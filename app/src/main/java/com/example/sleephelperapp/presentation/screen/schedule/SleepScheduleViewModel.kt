package com.example.sleephelperapp.presentation.screen.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sleephelperapp.domain.usecase.schedule.GetSleepScheduleUseCase
import com.example.sleephelperapp.domain.usecase.schedule.SetSleepScheduleUseCase
import com.example.sleephelperapp.domain.usecase.schedule.ToggleSleepSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SleepScheduleViewModel @Inject constructor(
    private val toggleSleepSettingUseCase: ToggleSleepSettingUseCase,
    private val getSleepScheduleUseCase: GetSleepScheduleUseCase,
    private val setSleepScheduleUseCase: SetSleepScheduleUseCase
):ViewModel(){
    val wakeUpTime: MutableLiveData<String> = MutableLiveData("07:00 AM")
    val sleepTime: MutableLiveData<String> = MutableLiveData("07:00 AM")
    val eyeComfortEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val notificationOffEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val doNotDisturbEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val flightModeEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val blackAndWhiteEnabled: MutableLiveData<Boolean> = MutableLiveData(false)

}