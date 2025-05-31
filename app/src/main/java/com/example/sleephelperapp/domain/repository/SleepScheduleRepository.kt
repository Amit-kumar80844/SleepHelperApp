package com.example.sleephelperapp.domain.repository

import com.example.sleephelperapp.data.model.SleepScheduleEntity
import kotlinx.coroutines.flow.Flow

interface SleepScheduleRepository {
    fun getSchedule(): Flow<SleepScheduleEntity?>
    suspend fun insertSchedule(schedule: SleepScheduleEntity)
    suspend fun updateSchedule(schedule: SleepScheduleEntity)
}