package com.sidharth.vidyakhoj.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sidharth.vidyakhoj.databinding.ActivityMainBinding
import com.sidharth.vidyakhoj.service.RefreshDataService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)
        lifecycleScope.launch {
            Intent(this@MainActivity, RefreshDataService::class.java).apply {
                action = RefreshDataService.Action.START.toString()
                delay(10000)
                startService(this)
            }
        }
    }
}