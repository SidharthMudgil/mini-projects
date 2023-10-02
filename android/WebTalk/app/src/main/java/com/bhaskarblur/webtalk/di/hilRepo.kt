package com.bhaskarblur.webtalk.di

import android.content.Context
import android.content.Intent
import com.bhaskarblur.webtalk.utils.firebaseWebRTCHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class hilRepo @Inject constructor(
    private val context: Context,
    private val firebaseWebRTCHandler: firebaseWebRTCHandler
) {

    fun startScreenShare(intent : Intent?) {
        firebaseWebRTCHandler.toggleScreenShare(true, intent!!);
    }

    fun stopScreenShare(intent : Intent?) {
        firebaseWebRTCHandler.toggleScreenShare(false, intent!!);
    }

    fun initWebRTCHandler(email:String) {
        firebaseWebRTCHandler.initWebRTCClient(email)
    }
}