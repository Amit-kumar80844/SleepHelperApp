package com.example.sleephelperapp.presentation.screen.schedule

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.provider.Settings
import android.view.View

class ScheduleService(private val context: Context) {
    fun enableBW(){
        val activity = context as? Activity ?: return
        val rootView = activity.findViewById<View>(android.R.id.content)
        rootView.setGrayscale(true)
    }
    fun disableBW(){
        val activity = context as? Activity ?: return
        val rootView = activity.findViewById<View>(android.R.id.content)
        rootView.setGrayscale(false)
    }
    fun enableEyeComfort(){
        val view = (context as? Activity)?.window?.decorView?.rootView ?: return
        val paint = run {
            val colorMatrix = ColorMatrix()
            colorMatrix.set(floatArrayOf(
                1f, 0f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f, 0f,
                0f, 0f, 0.5f, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            ))
            val filter = ColorMatrixColorFilter(colorMatrix)
            Paint().apply { colorFilter = filter }
        }
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }
    fun disableEyeComfort(){
        val view = (context as? Activity)?.window?.decorView?.rootView ?: return
        // Reset to default
        view.setLayerType(View.LAYER_TYPE_NONE, null)
    }
    fun enableNotificationOff(){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // For Android N and above, you need to grant Do Not Disturb access
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N &&
            !notificationManager.isNotificationPolicyAccessGranted) {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            context.startActivity(intent)
            // You might want to handle the case where the user doesn't grant permission
            return
        }
        notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
    }
    fun disableNotificationOff(){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
    }
    fun enableDoNotDisturb(){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N &&
            !notificationManager.isNotificationPolicyAccessGranted) {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            context.startActivity(intent)
            return
        }
        notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY)
    }
    fun disableDoNotDisturb(){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
    }
    fun enableFlightMod(){
        context.startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS))
    }
    fun disableFlightMod(){
        context.startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS))
    }
}
fun View.setGrayscale(enabled: Boolean) {
    if (enabled) {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        val paint = Paint()
        paint.colorFilter = colorFilter
        setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    } else {
        setLayerType(View.LAYER_TYPE_NONE, null)
    }
}