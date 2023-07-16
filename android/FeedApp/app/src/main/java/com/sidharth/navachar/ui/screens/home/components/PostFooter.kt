package com.sidharth.navachar.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sidharth.navachar.R
import com.sidharth.navachar.ui.theme.Blue600
import com.sidharth.navachar.ui.theme.Grey

@Composable
fun PostFooter(
    totalLikes: Int = 0,
    likes: Boolean = false,
    sortFilter: Boolean = false,
    comments: Boolean = false,
    share: Boolean = false,
    totalComments: Int = 0,
    onClick: () -> Unit = {}
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (likes) {
                FooterItem(
                    icon = mutableListOf(R.drawable.ic_like_filled, R.drawable.ic_like).random(),
                    label = if (likes and comments.not() and share.not() and sortFilter.not()) "like" else "$totalLikes likes",
                )
            }
            if (comments) {
                Box(modifier = Modifier.clickable {
                    onClick()
                }) {
                    FooterItem(
                        icon = R.drawable.ic_comment,
                        label = "$totalComments comments",
                        showIcon = sortFilter.not()
                    )
                }
            }
            if (share) {
                FooterItem(
                    icon = R.drawable.ic_share,
                    label = "share"
                )
            }
            if (sortFilter) {
                FooterItem(
                    icon = R.drawable.ic_sort,
                    label = "Recent",
                    sort = true
                )
            }
        }
    }
}

@Composable
fun FooterItem(
    icon: Int,
    label: String,
    showIcon: Boolean = true,
    sort: Boolean = false,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showIcon) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = if (sort or (icon == R.drawable.ic_like_filled)) Blue600 else Grey,
                modifier = Modifier
                    .padding(
                        end = if (sort) 3.dp else 3.dp
                    )
                    .size(if (sort) 14.dp else 18.dp)
            )
        }
        Text(
            text = label,
            color = if (sort) Blue600 else Grey,
            fontSize = 16.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PostFooterPreview() {
    PostFooter(
        likes = true,
        totalLikes = 123,
        comments = true,
        totalComments = 10,
        share = true,
    )
}