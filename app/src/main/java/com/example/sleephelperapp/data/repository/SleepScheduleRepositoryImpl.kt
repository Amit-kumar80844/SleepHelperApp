package com.example.sleephelperapp.data.repository

import com.example.sleephelperapp.data.local.dao.SleepScheduleDao
import com.example.sleephelperapp.data.model.SleepScheduleEntity
import com.example.sleephelperapp.domain.repository.SleepScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SleepScheduleRepositoryImpl @Inject constructor(
    private val dao: SleepScheduleDao
) : SleepScheduleRepository {
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


