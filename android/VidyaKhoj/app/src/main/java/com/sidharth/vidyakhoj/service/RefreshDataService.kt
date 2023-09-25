package com.sidharth.vidyakhoj.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
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
        when (intent?.action) {
            Action.START.toString() -> {}
            else -> stopSelf()
        }
        start()
        return START_NOT_STICKY
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setContentTitle("Syncing Data")
            .setContentText("Refreshing data from API")
            .build()

        startForeground(FOREGROUND_SERVICE_ID, notification)
        handler.postDelayed(refreshDataRunnable, 10000)
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

    enum class Action {
        START, STOP
    }
}