package com.example.sleephelperapp.data.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ScheduleToggleData {
    private val _scheduleEntity = MutableStateFlow<SleepScheduleEntity?>(null)
    val scheduleEntity: StateFlow<SleepScheduleEntity?> get() = _scheduleEntity
    fun setScheduleEntity(value: SleepScheduleEntity?) {
        _scheduleEntity.value = value
    }
}

