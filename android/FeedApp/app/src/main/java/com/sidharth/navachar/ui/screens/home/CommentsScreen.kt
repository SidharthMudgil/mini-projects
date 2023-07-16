package com.sidharth.navachar.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sidharth.navachar.ui.screens.home.components.Comment
import com.sidharth.navachar.ui.screens.home.components.Post
import com.sidharth.navachar.ui.screens.viewmodel.SharedViewModel

@Composable
fun CommentsScreen(
    sharedViewModel: SharedViewModel
) {
    val post = sharedViewModel.post!!
    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            Post(
                post = post,
                comments = true
            )
            LazyColumn {
                items(post.comments.size) { index ->
                    Comment(comment = post.comments[index])
                }
            }
        }
    }
}