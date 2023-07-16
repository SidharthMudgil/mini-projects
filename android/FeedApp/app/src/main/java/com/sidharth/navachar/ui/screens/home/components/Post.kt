package com.sidharth.navachar.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sidharth.navachar.R
import com.sidharth.navachar.domain.model.Post
import com.sidharth.navachar.ui.theme.Black

@Composable
fun Post(
    post: Post,
    onClick: () -> Unit = {},
    comments: Boolean = false,
) {
    Column(
        modifier = Modifier.padding(
            top = 10.dp,
            bottom = 10.dp
        )
    ) {
        Box(
            modifier = Modifier.padding(
                top = 10.dp,
                start = 15.dp,
                end = 15.dp,
            )
        ) {
            PostHeader(
                icon = R.drawable.ic_menu_horiz,
                image = post.author.image,
                type = post.type,
                title = post.author.name,
                subtitle = "2 hours ago",
            )
        }
        if (post.caption.isNotBlank()) {
            Box(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        start = 15.dp,
                        end = 15.dp,
                        bottom = 10.dp
                    )
                    .wrapContentHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = post.caption,
                    color = Black,
                    maxLines = 3,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Left,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        when (post.images.size) {
            0 -> Unit
            1 -> {
                Image(
                    painter = rememberAsyncImagePainter(post.images[0]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(0.dp))
                )
            }

            else -> {
                LazyRow {
                    items(post.images) { image ->
                        Image(
                            painter = rememberAsyncImagePainter(image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(start = 15.dp,)
                                .width(150.dp)
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier.padding(
                top = 10.dp,
                start = 15.dp,
                end = 15.dp,
            )
        ) {
            if (comments.not()) {
                PostFooter(
                    likes = true,
                    comments = true,
                    share = true,
                    totalLikes = post.likes,
                    totalComments = post.totalComments,
                    onClick = onClick,
                )
            } else {
                PostFooter(
                    comments = true,
                    totalComments = post.totalComments,
                    sortFilter = true,
                )
            }
        }
    }
}