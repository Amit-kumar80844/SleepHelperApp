package com.example.sleephelperapp.presentation.common

import java.util.Calendar
fun parseTime(time:String):Calendar{
    val calendar = Calendar.getInstance().apply {
        val parts = time.split(":", " ")
        var hour = parts[0].toInt()
        val minute = parts[1].toInt()
        val amPm = parts[2].uppercase()
        if (amPm == "PM" && hour < 12) hour += 12
        if (amPm == "AM" && hour == 12) hour = 0
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
        if (before(Calendar.getInstance())) {
            add(Calendar.DAY_OF_YEAR, 1)
        }
    }
    return calendar
}