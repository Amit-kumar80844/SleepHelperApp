package com.example.sleephelperapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sleephelperapp.data.local.dao.SleepScheduleDao
import com.example.sleephelperapp.data.model.SleepScheduleEntity

@Database(entities = [SleepScheduleEntity::class], version = 1)
abstract class SleepDatabase : RoomDatabase() {
    abstract fun sleepScheduleDao(): SleepScheduleDao
}