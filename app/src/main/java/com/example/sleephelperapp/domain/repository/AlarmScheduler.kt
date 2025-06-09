package com.example.sleephelperapp.domain.repository

interface AlarmScheduler {
    fun scheduleAlarm(time: String,messageTitle:String="",message:String = "")
    fun cancelAlarm()
    fun scheduleSetting(startTime:String,endTime:String)
}
