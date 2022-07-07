package com.example.music.ui.authentification.screen.free_registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.music.ui.common.top_bar.SimpleAppBar
import com.example.music.ui.theme.MusicTheme
import com.example.music.ui.theme.padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeRegistrationPassword(
    back: () -> Unit
) {
    Scaffold(
        topBar = {
            SimpleAppBar(back)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            FreeRegContent()
        }
    }
}

@Composable
fun FreeRegContent() {
    Column(
        modifier = Modifier.padding(
            start = MaterialTheme.padding.start,
            end = MaterialTheme.padding.end
        ),
    ) {
        Text(
            text = "Створіть пароль",
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier.padding(
                top = 50.dp
            ),
            lineHeight = 38.sp
        )

        TextField(
            value = "",
            onValueChange = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .scale(scaleY = 0.8F, scaleX = 1F),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Gray
            )
        )

        CreateAccountButton()
    }
}

@Composable
fun CreateAccountButton() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            ),
            modifier = Modifier.padding(
                top = 10.dp
            )
        ) {
            Text(
                text = "Створити акаунт",
                color = Color.Black
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FreeRegistrationPreview() {
    MusicTheme {
        FreeRegistrationPassword({})
    }
}
