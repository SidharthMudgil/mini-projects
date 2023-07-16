package com.sidharth.navachar.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Comments : Screen("comments_screen")
}
