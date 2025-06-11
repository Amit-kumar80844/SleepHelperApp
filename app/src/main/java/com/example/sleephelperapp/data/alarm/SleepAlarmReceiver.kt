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
    private fun sleepStart(context: Context){
       val scheduleData = ScheduleToggleData().getScheduleEntity()
        val scheduleService = ScheduleService(context)
        if(scheduleData.value?.eyeComfortEnabled == true){
            scheduleService.enableEyeComfort()
        }else if(scheduleData.value?.blackAndWhiteEnabled == true){
            scheduleService.enableBW()
        }else if(scheduleData.value?.notificationOffEnabled == true){
            scheduleService.enableNotificationOff()
        }else if(scheduleData.value?.doNotDisturbEnabled == true){
            scheduleService.enableDoNotDisturb()
        }else if(scheduleData.value?.flightModeEnabled == true){
            scheduleService.enableFlightMod()
        }
    }
    private fun sleepEnd(context: Context){
        val scheduleData = ScheduleToggleData().getScheduleEntity()
        val scheduleService = ScheduleService(context)
        if(scheduleData.value?.eyeComfortEnabled == true){
            scheduleService.disableEyeComfort()
        }else if(scheduleData.value?.blackAndWhiteEnabled == true){
            scheduleService.disableBW()
        }else if(scheduleData.value?.notificationOffEnabled == true){
            scheduleService.disableNotificationOff()
        }else if(scheduleData.value?.doNotDisturbEnabled == true){
            scheduleService.disableDoNotDisturb()
        }else if(scheduleData.value?.flightModeEnabled == true){
            scheduleService.disableFlightMod()
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
