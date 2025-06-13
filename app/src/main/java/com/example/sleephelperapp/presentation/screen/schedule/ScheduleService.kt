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
import android.view.Window
import androidx.core.content.ContextCompat

class ScheduleService(private val context: Context) {
    companion object {
        private const val TAG = "ScheduleService"
    }

    /**
     * Applies a grayscale effect to the entire activity window.
     * This is more efficient than recursively traversing the view hierarchy.
     */
    fun enableBW() {
        setHardwareLayerSaturation(0f)
    }

    /**
     * Removes the grayscale effect from the activity window.
     */
    fun disableBW() {
        setHardwareLayerSaturation(1f)
    }

    /**
     * Applies a blue light filter to the entire activity window.
     */
    fun enableEyeComfort() {
        val colorMatrix = ColorMatrix().apply {
            set(floatArrayOf(
                1.0f, 0.0f, 0.0f, 0.0f, 0.0f,    // Red channel
                0.0f, 0.9f, 0.0f, 0.0f, 0.0f,    // Green channel (slightly reduced)
                0.0f, 0.0f, 0.7f, 0.0f, 0.0f,    // Blue channel (reduced)
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f     // Alpha channel
            ))
        }
        setHardwareLayerColorFilter(ColorMatrixColorFilter(colorMatrix))
    }

    /**
     * Removes the blue light filter from the activity window.
     */
    fun disableEyeComfort() {
        setHardwareLayerColorFilter(null)
    }

    /**
     * Enables Do Not Disturb mode, silencing all notifications.
     * Requires Notification Policy Access permission.
     * @return true if successful, false otherwise.
     */
    fun enableDoNotDisturb(): Boolean {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            ?: return false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!notificationManager.isNotificationPolicyAccessGranted) {
                Log.w(TAG, "Notification policy access not granted.")
                requestNotificationPolicyAccess()
                return false
            }
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY)
            Log.d(TAG, "Do Not Disturb enabled successfully.")
            return true
        } else {
            // For older versions, this is the closest equivalent
            // This requires the DND permission but is deprecated.
            // Consider handling this case based on your target audience.
            Log.w(TAG, "Do Not Disturb not fully supported below Android M.")
            return false
        }
    }

    /**
     * Disables Do Not Disturb mode, allowing all notifications.
     * @return true if successful, false otherwise.
     */
    fun disableDoNotDisturb(): Boolean {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            ?: return false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!notificationManager.isNotificationPolicyAccessGranted) {
                Log.w(TAG, "Notification policy access not granted.")
                requestNotificationPolicyAccess()
                return false
            }
        }
        notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
        Log.d(TAG, "Do Not Disturb disabled successfully.")
        return true
    }

    /**
     * Turns off all notifications.
     * Requires Notification Policy Access permission.
     * @return true if successful, false otherwise.
     */
    fun enableNotificationOff(): Boolean {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                ?: return false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!notificationManager.isNotificationPolicyAccessGranted) {
                Log.w(TAG, "Notification policy access not granted.")
                requestNotificationPolicyAccess()
                return false
            }
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
            Log.d(TAG, "Notifications turned off successfully.")
            return true
        } else {
            Log.w(TAG, "Turning off notifications is not supported below Android M.")
            return false
        }
    }

    /**
     * Turns notifications back on to the default state (all).
     * @return true if successful, false otherwise.
     */
    fun disableNotificationOff(): Boolean {
        return disableDoNotDisturb() // Functionally the same
    }


    /**
     * Opens the Airplane Mode settings screen.
     * Direct programmatic toggling of Airplane Mode is restricted by Android for security reasons.
     */
    fun toggleFlightModeSettings() {
        val intent = Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Log.e(TAG, "Could not open Airplane Mode settings.")
        }
    }

    /**
     * Checks if the app has been granted Notification Policy Access.
     * @return true if access is granted, false otherwise.
     */
    fun hasNotificationPolicyAccess(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val notificationManager = ContextCompat.getSystemService(context, NotificationManager::class.java)
            return notificationManager?.isNotificationPolicyAccessGranted == true
        }
        return false // Not required for older APIs
    }


    /**
     * Launches an intent to the settings screen where the user can grant
     * Notification Policy Access.
     */
    private fun requestNotificationPolicyAccess() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                Log.e(TAG, "Could not open notification policy settings", e)
            }
        }
    }

    private fun setHardwareLayerColorFilter(colorFilter: ColorMatrixColorFilter?) {
        val activity = context as? Activity ?: return
        val window: Window = activity.window
        val decorView = window.decorView

        if (colorFilter != null) {
            val paint = Paint().apply { this.colorFilter = colorFilter }
            decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
        } else {
            decorView.setLayerType(View.LAYER_TYPE_NONE, null)
        }
    }

    private fun setHardwareLayerSaturation(saturation: Float) {
        val colorMatrix = ColorMatrix().apply {
            setSaturation(saturation)
        }
        setHardwareLayerColorFilter(ColorMatrixColorFilter(colorMatrix))
    }
}