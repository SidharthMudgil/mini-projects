package com.sidharth.vidyakhoj.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sidharth.vidyakhoj.databinding.ActivityMainBinding
import com.sidharth.vidyakhoj.service.RefreshDataService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)
        Intent(this, RefreshDataService::class.java).apply {
            startForegroundService(this)
        }
    }
}