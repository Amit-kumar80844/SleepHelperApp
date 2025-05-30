package com.example.sleephelperapp.data.repository

import com.example.sleephelperapp.data.local.dao.SleepScheduleDao
import com.example.sleephelperapp.data.model.SleepScheduleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SleepScheduleRepositoryImpl @Inject constructor(
    private val dao: SleepScheduleDao
) :SleepScheduleDao{
    override fun getSchedule(): Flow<SleepScheduleEntity?> {
     return dao.getSchedule()
    }
    override suspend fun insertSchedule(schedule: SleepScheduleEntity) {
        dao.insertSchedule(schedule)
    }
    override suspend fun updateSchedule(schedule: SleepScheduleEntity) {
        dao.updateSchedule(schedule)
    }
}


