package com.sidharth.navachar.ui.screens.home.tabpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sidharth.navachar.ui.theme.Blue600

@Composable
fun BazaarPage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Bazaar Page",
            color = Blue600,
            fontSize = 48.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BazaarPagePreview() {
    BazaarPage()
}