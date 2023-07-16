package com.sidharth.navachar.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sidharth.navachar.ui.screens.home.components.TabLayout
import com.sidharth.navachar.ui.screens.home.tabpage.BazaarPage
import com.sidharth.navachar.ui.screens.home.tabpage.CharchaPage
import com.sidharth.navachar.ui.screens.home.tabpage.ProfilePage
import com.sidharth.navachar.ui.screens.viewmodel.FeedViewModel
import com.sidharth.navachar.ui.screens.viewmodel.SharedViewModel
import com.sidharth.navachar.ui.theme.NavacharTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    feedViewModel: FeedViewModel,
    sharedViewModel: SharedViewModel,
) {
    val tabs = listOf("charcha", "bazaar", "profile")
    var selectedTab by remember { mutableStateOf(0) }

    NavacharTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    TabLayout(
                        tabs = tabs,
                        selectedTabIndex = selectedTab,
                        onTabSelected = {
                            selectedTab = it
                        })
                },
            ) { padding ->
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(padding).fillMaxSize()
                ) {
                    when (selectedTab) {
                        0 -> CharchaPage(navHostController, feedViewModel, sharedViewModel)
                        1 -> BazaarPage()
                        2 -> ProfilePage()
                    }
                }
            }
        }
    }
}