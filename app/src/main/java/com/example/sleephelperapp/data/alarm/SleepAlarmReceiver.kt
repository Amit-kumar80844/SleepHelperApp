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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sleephelperapp.data.model.ScheduleToggleData
import com.example.sleephelperapp.presentation.screen.schedule.ScheduleService
import com.example.sleephelperapp.presentation.screen.schedule.SleepScheduleViewModel

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
            Log.d("SleepAlarmReceiver", "Eye comfort enabled")
        }
        if (schedule?.blackAndWhiteEnabled == true) {
            scheduleService.enableBW()
            Log.d("SleepAlarmReceiver", "Black and white enabled")
        }
        if (schedule?.notificationOffEnabled == true) {
            scheduleService.enableNotificationOff()
            Log.d("SleepAlarmReceiver", "Notification off enabled")
        }
        if (schedule?.doNotDisturbEnabled == true) {
            scheduleService.enableDoNotDisturb()
            Log.d("SleepAlarmReceiver", "Do not disturb enabled")
        }
        if (schedule?.flightModeEnabled == true) {
            scheduleService.enableFlightMod()
            Log.d("SleepAlarmReceiver", "Flight mode enabled")
        }
    }

    private fun sleepEnd(context: Context){
        val scheduleData = ScheduleToggleData.scheduleEntity.value
        val scheduleService = ScheduleService(context)
        if(scheduleData?.eyeComfortEnabled == true){
            scheduleService.disableEyeComfort()
            Log.d("SleepAlarmReceiver", "Eye comfort disabled")
        }
        if(scheduleData?.blackAndWhiteEnabled == true){
            scheduleService.disableBW()
            Log.d("SleepAlarmReceiver", "Black and white disabled")
        }
        if(scheduleData?.notificationOffEnabled == true){
            scheduleService.disableNotificationOff()
            Log.d("SleepAlarmReceiver", "Notification off disabled")
        }
        if(scheduleData?.doNotDisturbEnabled == true){
            scheduleService.disableDoNotDisturb()
            Log.d("SleepAlarmReceiver", "Do not disturb disabled")
        }
        if(scheduleData?.flightModeEnabled == true){
            scheduleService.disableFlightMod()
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
