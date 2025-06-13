package com.example.sleephelperapp.data.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.sleephelperapp.data.model.ScheduleToggleData
import com.example.sleephelperapp.presentation.screen.schedule.ScheduleService

class SleepAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            "com.example.sleephelperapp.SLEEP_ALARM_TRIGGERED"->{
                alarmTrigger(intent,context)
            }
            "com.example.sleephelperapp.SLEEP_START" -> {
                Log.d("SleepAlarmReceiver", "Start alarm triggered")
                sleepStart(context)
            }
            "com.example.sleephelperapp.SLEEP_END" -> {
                Log.d("SleepAlarmReceiver", "End alarm triggered")
                sleepEnd(context)
            }
            else -> {
                Log.w("SleepAlarmReceiver", "Unknown alarm action: ${intent.action}")
            }
        }
    }

    private fun sleepStart(context: Context) {
        val schedule = ScheduleToggleData.scheduleEntity.value
        val scheduleService = ScheduleService(context)
        if (schedule?.eyeComfortEnabled == true) {
            scheduleService.enableEyeComfort()
            showNotification(context, "Eye Comfort Enabled", "Eye comfort enabled")
            Log.d("SleepAlarmReceiver", "Eye comfort enabled")
        }
        if (schedule?.blackAndWhiteEnabled == true) {
            scheduleService.enableBW()
            showNotification(context , "Black and White Enabled", "Black and white enabled")
            Log.d("SleepAlarmReceiver", "Black and white enabled")
        }
        if (schedule?.notificationOffEnabled == true) {
            scheduleService.enableNotificationOff()
            showNotification(context , "Notification Off Enabled", "Notification off enabled")
            Log.d("SleepAlarmReceiver", "Notification off enabled")
        }
        if (schedule?.doNotDisturbEnabled == true) {
            scheduleService.enableDoNotDisturb()
            showNotification(context , "Do Not Disturb Enabled", "Do not disturb enabled")
            Log.d("SleepAlarmReceiver", "Do not disturb enabled")
        }
        if (schedule?.flightModeEnabled == true) {
            scheduleService.toggleFlightModeSettings()
            showNotification(context , "Flight Mode Enabled", "Flight mode enabled")
            Log.d("SleepAlarmReceiver", "Flight mode enabled")
        }
    }

    private fun sleepEnd(context: Context){
        val scheduleData = ScheduleToggleData.scheduleEntity.value
        val scheduleService = ScheduleService(context)
        if(scheduleData?.eyeComfortEnabled == true){
            scheduleService.disableEyeComfort()
            showNotification(context , "Eye Comfort Disabled", "Eye comfort disabled")
            Log.d("SleepAlarmReceiver", "Eye comfort disabled")
        }
        if(scheduleData?.blackAndWhiteEnabled == true){
            scheduleService.disableBW()
            showNotification(context ,  "Black and White Disabled", "Black and white disabled")
            Log.d("SleepAlarmReceiver", "Black and white disabled")
        }
        if(scheduleData?.notificationOffEnabled == true){
            scheduleService.disableNotificationOff()
            showNotification(context , "Notification Off Disabled", "Notification off disabled")
            Log.d("SleepAlarmReceiver", "Notification off disabled")
        }
        if(scheduleData?.doNotDisturbEnabled == true){
            scheduleService.disableDoNotDisturb()
            showNotification(context , "Do Not Disturb Disabled", "Do not disturb disabled")
            Log.d("SleepAlarmReceiver", "Do not disturb disabled")
        }
        if(scheduleData?.flightModeEnabled == true){
            scheduleService.toggleFlightModeSettings()
            showNotification(context , "Flight Mode Disabled", "Flight mode disabled")
            Log.d("SleepAlarmReceiver", "Flight mode disabled")
        }
    }
    private fun alarmTrigger(intent : Intent,context: Context){
        val title = intent.getStringExtra("title") ?: "Sleep Alarm"
        val message = intent.getStringExtra("message") ?: "Time to sleep!"
        try {
            val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            RingtoneManager.getRingtone(context, ringtoneUri)?.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        showNotification(context, title, message)
    }
    private fun showNotification(context: Context, messageTitle:String="",message:String = "") {
        val channelId = "sleep_alarm_channel"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Sleep Alarm",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(messageTitle)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager.notify(0, notification)
    }
}
