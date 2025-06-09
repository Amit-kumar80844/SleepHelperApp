package com.example.sleephelperapp.data.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.net.toUri
import com.example.sleephelperapp.data.alarm.SleepAlarmReceiver
import com.example.sleephelperapp.domain.repository.AlarmScheduler
import com.example.sleephelperapp.presentation.common.parseTime
import java.util.Calendar

class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {
    override fun scheduleAlarm(time: String, messageTitle: String, message: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // Android 12+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = "package:${context.packageName}".toUri()
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
            Log.w("AlarmScheduler", "Exact alarm permission required on Android 12+")
            return
        }

        // Build intent with extra data
        val intent = Intent(context, SleepAlarmReceiver::class.java).apply {
            action = "com.example.sleephelperapp.SLEEP_ALARM_TRIGGERED"
            putExtra("title", messageTitle)
            putExtra("message", message)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1001,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val calendar = parseTime(time)
        Log.d("AlarmScheduler", "Alarm set for: ${calendar.time}")
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
    override fun cancelAlarm() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, SleepAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1001,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
        Log.d("AlarmScheduler", "Alarm cancelled")
    }

    override fun scheduleSetting(startTime: String, endTime: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // start time
        val intentStart = Intent(context, SleepAlarmReceiver::class.java).apply {
            action = "com.example.sleephelperapp.SLEEP_START"
        }
        val pendingStart = PendingIntent.getBroadcast(
            context,
            2001,
            intentStart,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val calendarStart = parseTime(startTime).apply {
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendarStart.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingStart
        )
        //  End Time
        val intentEnd = Intent(context, SleepAlarmReceiver::class.java).apply {
            action = "com.example.sleephelperapp.SLEEP_END"
        }
        val pendingEnd = PendingIntent.getBroadcast(
            context,
            2002,
            intentEnd,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val calendarEnd = parseTime(endTime).apply {
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendarEnd.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingEnd
        )
    }

}
