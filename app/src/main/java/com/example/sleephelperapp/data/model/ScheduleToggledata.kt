package com.example.sleephelperapp.data.model

import kotlinx.coroutines.flow.StateFlow

class ScheduleToggleData{
    private lateinit var scheduleEntity: StateFlow<SleepScheduleEntity?>
    fun getScheduleEntity(): StateFlow<SleepScheduleEntity?> = scheduleEntity
    fun setScheduleEntity(value: StateFlow<SleepScheduleEntity?>) {
        this.scheduleEntity = value
    }
}