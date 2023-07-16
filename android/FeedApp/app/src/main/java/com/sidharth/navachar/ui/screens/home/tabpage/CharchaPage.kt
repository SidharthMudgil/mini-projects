package com.sidharth.navachar.ui.screens.home.tabpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.sidharth.navachar.navigation.Screen
import com.sidharth.navachar.ui.screens.home.components.Post
import com.sidharth.navachar.ui.screens.viewmodel.FeedViewModel
import com.sidharth.navachar.ui.screens.viewmodel.SharedViewModel

@Composable
fun CharchaPage(
    navHostController: NavHostController,
    feedViewModel: FeedViewModel,
    sharedViewModel: SharedViewModel
) {
    val posts = feedViewModel.postsPager.collectAsLazyPagingItems()

    LazyColumn {
        items(posts.itemCount) { index ->
            posts[index]?.let {
                Post(post = it,
                    onClick = {
                        sharedViewModel.addPost(it)
                        navHostController.navigate(Screen.Comments.route)
                    })
            }
        }
        when (posts.loadState.append) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> item { Loading() }
            else -> item {}
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(28.dp)
                .height(28.dp)
                .padding(8.dp),
            strokeWidth = 3.dp
        )
    }
}