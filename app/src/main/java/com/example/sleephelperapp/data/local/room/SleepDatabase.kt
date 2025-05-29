package com.example.sleephelperapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sleephelperapp.data.local.dao.SleepScheduleDao
import com.example.sleephelperapp.data.model.SleepScheduleEntity

@Database(entities = [SleepScheduleEntity::class], version = 1)
abstract class SleepDatabase : RoomDatabase() {
    abstract fun sleepScheduleDao(): SleepScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getDatabase(context: Context): SleepDatabase {
            if(INSTANCE != null) return INSTANCE!!
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SleepDatabase::class.java,
                    "sleep_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
