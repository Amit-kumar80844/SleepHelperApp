package com.example.sleephelperapp.domain.repository

interface AlarmScheduler {
    fun scheduleSleepAlarm(time: String)
    fun scheduleWakeUpAlarm(time: String)
    fun cancelSleepAlarm()
    fun cancelWakeUpAlarm()
}
