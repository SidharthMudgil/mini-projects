package com.sidharth.navachar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sidharth.navachar.di.BaseApplication
import com.sidharth.navachar.navigation.SetupNavGraph
import com.sidharth.navachar.ui.screens.viewmodel.FeedViewModel
import com.sidharth.navachar.ui.screens.viewmodel.FeedViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    @Inject
    lateinit var feedViewModelFactory: FeedViewModelFactory

    private val feedViewModel: FeedViewModel by viewModels {
        feedViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as BaseApplication).appComponent.inject(this)

        setContent {
            navController = rememberNavController()
            SetupNavGraph(
                navHostController = navController,
                feedViewModel = feedViewModel,
            )
        }
    }
}