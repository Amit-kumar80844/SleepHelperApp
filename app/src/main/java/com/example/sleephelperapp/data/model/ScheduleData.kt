package com.example.sleephelperapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_schedule")
data class SleepScheduleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val wakeUpTime: String = "07:00 AM",
    val sleepTime: String = "11:00 PM",
    val wakeUpEnabled: Boolean = false,
    val sleepAlarmEnabled: Boolean = false,
    val eyeComfortEnabled: Boolean = false,
    val notificationOffEnabled: Boolean = false,
    val doNotDisturbEnabled: Boolean = false,
    val flightModeEnabled: Boolean = false,
    val blackAndWhiteEnabled: Boolean = false
)
