package com.example.music.ui.common.top_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.music.R
import com.example.music.ui.theme.padding

@Composable
fun SimpleAppBar(back: () -> Unit) {
    Row(
        modifier = Modifier.background(Color.Black)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow_24),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .clickable {
                    back.invoke()
                }
                .padding(start = MaterialTheme.padding.start)
        )

        Text(
            text = "Створення акаунту",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}