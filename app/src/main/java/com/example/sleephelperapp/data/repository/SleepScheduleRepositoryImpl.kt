package com.example.sleephelperapp.data.repository

import com.example.sleephelperapp.data.local.dao.SleepScheduleDao
import com.example.sleephelperapp.data.model.SleepScheduleEntity
import kotlinx.coroutines.flow.Flow

class SleepRepository(private val dao: SleepScheduleDao) {

    val scheduleFlow: Flow<SleepScheduleEntity?> = dao.getSchedule()

    suspend fun saveOrUpdate(schedule: SleepScheduleEntity) {
        dao.insertSchedule(schedule)
    }

    suspend fun update(schedule: SleepScheduleEntity) {
        dao.updateSchedule(schedule)
    }
}

