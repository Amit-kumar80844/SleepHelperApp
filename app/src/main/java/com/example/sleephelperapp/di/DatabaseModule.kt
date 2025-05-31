package com.example.sleephelperapp.di

import android.content.Context
import androidx.room.Room
import com.example.sleephelperapp.data.local.dao.SleepScheduleDao
import com.example.sleephelperapp.data.local.room.SleepDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideSleepDatabase(@ApplicationContext context: Context): SleepDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SleepDatabase::class.java,
            name="sleep_db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideSleepScheduleDao(database: SleepDatabase): SleepScheduleDao {
        return database.sleepScheduleDao()
    }
}
