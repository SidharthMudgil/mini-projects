package com.sidharth.vidyakhoj

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.sidharth.vidyakhoj.service.RefreshDataService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VidyaKhojApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            RefreshDataService.CHANNEL_ID,
            "Running Notifications",
            NotificationManager.IMPORTANCE_HIGH,
        )
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}