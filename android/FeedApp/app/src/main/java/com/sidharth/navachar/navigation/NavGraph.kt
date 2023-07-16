package com.sidharth.navachar.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sidharth.navachar.domain.model.Post
import com.sidharth.navachar.ui.screens.home.CommentsScreen
import com.sidharth.navachar.ui.screens.home.HomeScreen
import com.sidharth.navachar.ui.screens.viewmodel.FeedViewModel
import com.sidharth.navachar.ui.screens.viewmodel.SharedViewModel

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    feedViewModel: FeedViewModel,
) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(
                navHostController,
                feedViewModel,
                sharedViewModel,
            )
        }
        composable(
            route = Screen.Comments.route
        ) {
            CommentsScreen(sharedViewModel)
        }
    }
}