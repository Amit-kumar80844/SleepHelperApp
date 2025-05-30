package com.example.sleephelperapp.domain.usecase.schedule

import com.example.sleephelperapp.data.local.dao.SleepScheduleDao
import com.example.sleephelperapp.data.model.SleepScheduleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToggleSleepSettingUseCase @Inject constructor(private val dao: SleepScheduleDao) {
    suspend operator fun invoke(key: String, value: Boolean, current: SleepScheduleEntity) {
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

class GetSleepScheduleUseCase @Inject constructor(private val dao: SleepScheduleDao) {
     suspend operator fun invoke(): Flow<SleepScheduleEntity?> {
        return dao.getSchedule()
    }
}

class SetSleepScheduleUseCase @Inject constructor(private val dao :SleepScheduleDao) {
    suspend operator fun invoke(schedule: SleepScheduleEntity) {
        dao.insertSchedule(schedule)
    }
}