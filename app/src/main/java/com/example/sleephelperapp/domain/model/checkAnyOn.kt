package com.example.sleephelperapp.domain.model

fun checkAnyOn(
    wakeUpAlarmEnabled: Boolean,
    sleepTimeAlarmEnabled: Boolean,
    blackAndWhiteScreenEnabled: Boolean,
    eyeComfortEnabled: Boolean,
    notificationOffEnabled: Boolean,
    doNotDisturbEnabled: Boolean,
    flightModeEnabled: Boolean
):Boolean {
    return wakeUpAlarmEnabled || sleepTimeAlarmEnabled || blackAndWhiteScreenEnabled || eyeComfortEnabled || notificationOffEnabled || doNotDisturbEnabled || flightModeEnabled
}