package com.example.sleephelperapp.data.local.dao

import androidx.room.*
import com.example.sleephelperapp.data.model.SleepScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepScheduleDao {
    @Query("SELECT * FROM sleep_schedule LIMIT 1")
    fun getSchedule(): Flow<SleepScheduleEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSchedule(schedule: SleepScheduleEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateSchedule(schedule: SleepScheduleEntity)
}
