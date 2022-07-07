package com.example.music.ui.authentification.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.music.R
import com.example.music.ui.theme.MusicTheme

@Composable
fun AuthMain(
    toFreeRegistration: () -> Unit
) {
    AuthMainContent(toFreeRegistration)
}

@Composable
fun AuthMainContent(
    toFreeRegistration: () -> Unit
) {
    val painter = painterResource(id = R.drawable.ic_launcher_foreground)
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.Black)
        ) {
            Image(
                contentDescription = "",
                painter = painter,
                modifier = Modifier.aspectRatio(
                    ratio = painter.intrinsicSize.height /
                            painter.intrinsicSize.width
                ),
                contentScale = ContentScale.Fit
            )
        }

        Box(modifier = Modifier.weight(1f)) {
            MainBottomContent(toFreeRegistration)
        }
    }
}

@Composable
fun MainBottomContent(
    toFreeRegistration: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_fitbit_24),
            contentDescription = "",
            Modifier.background(Color.White)
        )

        Text(
            text = "Мільйони пінесь.\nСлухайте безкоштовно в Spotify",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                top = 10.dp,
                bottom = 20.dp
            )
        )

        MainButtons(toFreeRegistration)
    }
}

@Composable
fun MainButtons(
    toFreeRegistration: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                toFreeRegistration.invoke()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            Text(
                text = "Зареєструватись безкоштовно",
                color = Color.Black,
            )
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Text(text = "Продовжити за допомогою Google")
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Text(text = "Продовжити через Facebook")
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
        ) {
            Text(text = "Увійти")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    MusicTheme {

    }
}
