package com.sidharth.vidyakhoj.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import com.sidharth.vidyakhoj.R
import com.sidharth.vidyakhoj.data.UniversityApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RefreshDataService : Service() {
    private val handler = Handler(Looper.getMainLooper())

    @Inject
    lateinit var universityApi: UniversityApi

    private val refreshDataRunnable = object : Runnable {
        override fun run() {
            handler.postDelayed(this, 10000)
            start()
        }
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

        handler.post(refreshDataRunnable)
    }

    private fun fetchDataFromApi() {
        GlobalScope.launch {
            Log.d("s", universityApi.getUniversities().toString())
        }
    }

    companion object {
        private const val FOREGROUND_SERVICE_ID = 101
        const val CHANNEL_ID = "DataRefreshServiceChannel"
    }

    enum class Action {
        START, STOP
    }
}