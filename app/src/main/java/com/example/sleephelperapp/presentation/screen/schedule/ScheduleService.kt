package com.example.sleephelperapp.presentation.screen.schedule

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.View

class ScheduleService(private val context: Context) {

    fun enableBW() {
        val activity = context as? Activity ?: return
        val rootView = activity.findViewById<View>(android.R.id.content)
        rootView.setGrayscale(true)
    }

    fun disableBW() {
        val activity = context as? Activity ?: return
        val rootView = activity.findViewById<View>(android.R.id.content)
        rootView.setGrayscale(false)
    }

    fun enableEyeComfort() {
        val view = (context as? Activity)?.window?.decorView?.rootView ?: return
        val colorMatrix = ColorMatrix().apply {
            set(floatArrayOf(
                1f, 0f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f, 0f,
                0f, 0f, 0.5f, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            ))
        }
        val filter = ColorMatrixColorFilter(colorMatrix)
        val paint = Paint().apply { colorFilter = filter }
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }

    fun disableEyeComfort() {
        val view = (context as? Activity)?.window?.decorView?.rootView ?: return
        view.setLayerType(View.LAYER_TYPE_NONE, null)
    }

    fun enableNotificationOff() {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (notificationManager.isNotificationPolicyAccessGranted) {
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
                Log.d("ScheduleService", "Notification filter set to NONE")
            } else {
                Log.w("ScheduleService", "Notification policy access not granted")
                // Optional: Prompt user via UI if context is an Activity
            }
        } else {
            Log.w("ScheduleService", "Notification policy changes not supported below Android N")
        }
    }

    fun disableNotificationOff() {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
    }

    fun enableDoNotDisturb() {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (notificationManager.isNotificationPolicyAccessGranted) {
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY)
            } else {
                Log.w("ScheduleService", "Do Not Disturb access not granted")
            }
        }
    }

    fun disableDoNotDisturb() {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
    }

    fun enableFlightMod() {
        context.startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

    fun disableFlightMod() {
        // NOTE: You can't programmatically disable airplane mode on Android 4.2+ (security restriction)
        context.startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }
}

fun View.setGrayscale(enabled: Boolean) {
    if (enabled) {
        val colorMatrix = ColorMatrix().apply { setSaturation(0f) }
        val paint = Paint().apply {
            colorFilter = ColorMatrixColorFilter(colorMatrix)
        }
        setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    } else {
        setLayerType(View.LAYER_TYPE_NONE, null)
    }
}
