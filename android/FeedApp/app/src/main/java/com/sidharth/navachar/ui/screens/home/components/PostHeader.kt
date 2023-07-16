package com.sidharth.navachar.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sidharth.navachar.R
import com.sidharth.navachar.domain.model.PostType
import com.sidharth.navachar.ui.theme.Black
import com.sidharth.navachar.ui.theme.Blue600
import com.sidharth.navachar.ui.theme.Blue100
import com.sidharth.navachar.ui.theme.Grey

@Composable
fun PostHeader(
    icon: Int,
    image: String,
    type: PostType = PostType.QUESTION,
    title: String,
    subtitle: String,
    showType: Boolean = true,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 5.dp)
                .size(48.dp)
                .clip(CircleShape)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    color = Black,
                )
                if (showType) {
                    Text(
                        text = type.name,
                        color = Blue600,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .background(Blue100)
                            .padding(3.dp)
                    )
                }
            }
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Grey,
            )
        }
        Box(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PostHeaderPreview() {
    PostHeader(
        image = "https://static.boredpanda.com/blog/wp-content/uploads/2014/12/Top-10-photographers-for-travel-portraits14__700.jpg",
        type = PostType.MARKETING,
        title = "Sidharth Mudgil",
        subtitle = "2 hours ago",
        icon = R.drawable.ic_menu_horiz
    )
}