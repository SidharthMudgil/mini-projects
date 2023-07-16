package com.sidharth.navachar.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sidharth.navachar.R
import com.sidharth.navachar.domain.model.Comment
import com.sidharth.navachar.ui.theme.Black

@Composable
fun Comment(comment: Comment) {
    Column(
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Box(
            modifier = Modifier.padding(
                top = 15.dp,
                start = 15.dp,
                end = 15.dp,
            )
        ) {
            PostHeader(
                icon = R.drawable.ic_menu_vert,
                image = comment.author.image,
                showType = false,
                title = comment.author.name,
                subtitle = "Public",
            )
        }
        Box(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    start = 15.dp,
                    end = 15.dp,
                )
                .wrapContentHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = comment.comment,
                color = Black,
                maxLines = 2,
                fontSize = 16.sp,
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Box(
            modifier = Modifier.padding(
                top = 10.dp,
                start = 15.dp,
                end = 15.dp,
            )
        ) {
            PostFooter(
                likes = true,
                totalLikes = comment.likes
            )
        }
    }
}