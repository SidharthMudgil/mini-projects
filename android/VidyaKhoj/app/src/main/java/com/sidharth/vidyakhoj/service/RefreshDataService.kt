package com.sidharth.vidyakhoj.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.sidharth.vidyakhoj.R


class RefreshDataService : Service() {
    private val handler = Handler(Looper.getMainLooper())

    private val refreshDataRunnable = Runnable {
        fetchDataFromApi()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            packageManager.getLaunchIntentForPackage(this.packageName),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setContentTitle("Syncing Data")
            .setContentText("Refreshing data from API")
            .build()

        startForeground(FOREGROUND_SERVICE_ID, notification)
        handler.post(refreshDataRunnable)

        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        val restartServiceIntent = Intent(applicationContext, this.javaClass)
        restartServiceIntent.setPackage(packageName)

        val restartServicePendingIntent = PendingIntent.getService(
            applicationContext,
            1,
            restartServiceIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmService = applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmService[AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000] =
            restartServicePendingIntent
        super.onTaskRemoved(rootIntent)
    }

    private fun fetchDataFromApi() {
        Intent().apply {
            action = ACTION_REFRESH
            sendBroadcast(this)
            LocalBroadcastManager.getInstance(this@RefreshDataService).sendBroadcast(this)
        }
        handler.postDelayed(refreshDataRunnable, 10000)
    }

    companion object {
        private const val FOREGROUND_SERVICE_ID = 101
        const val CHANNEL_ID = "DataRefreshServiceChannel"
        const val ACTION_REFRESH = "updateData"
    }
}