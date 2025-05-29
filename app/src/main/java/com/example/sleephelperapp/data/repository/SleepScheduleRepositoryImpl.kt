package com.example.sleephelperapp.data.repository

import com.example.sleephelperapp.data.local.dao.SleepScheduleDao
import com.example.sleephelperapp.data.model.SleepScheduleEntity
import kotlinx.coroutines.flow.Flow

class SleepRepository(private val dao: SleepScheduleDao) {

    val scheduleFlow: Flow<SleepScheduleEntity?> = dao.getSchedule()

    suspend fun saveSchedule(schedule: SleepScheduleEntity) {
        dao.insertSchedule(schedule)
    }

    suspend fun updateToggle(key: String, value: Boolean, current: SleepScheduleEntity) {
        val updated = when (key) {
            "wakeUp" -> current.copy(wakeUpEnabled = value)
            "sleepAlarm" -> current.copy(sleepAlarmEnabled = value)
            "eyeComfort" -> current.copy(eyeComfortEnabled = value)
            "notificationOff" -> current.copy(notificationOffEnabled = value)
            "doNotDisturb" -> current.copy(doNotDisturbEnabled = value)
            "flightMode" -> current.copy(flightModeEnabled = value)
            "bwScreen" -> current.copy(blackAndWhiteEnabled = value)
            else -> current
        }
        dao.updateSchedule(updated)
    }
}
