package com.example.music.ui.authentification.screen.log_in

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.music.ui.authentification.RegistrationScreen
import com.example.music.ui.common.field.EmailField
import com.example.music.ui.common.field.PasswordField
import com.example.music.ui.common.top_bar.SimpleAppBar
import com.example.music.ui.theme.MusicTheme
import com.example.music.ui.theme.padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogIn(
    navController: NavController
) {
    Scaffold(
        topBar = {
            SimpleAppBar(
                navController,
                title = "Створення акаунту"
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            LogInContent(navController)
        }
    }
}

@Composable
fun LogInContent(
    navController: NavController
) {
    var mail by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(
            start = MaterialTheme.padding.start,
            end = MaterialTheme.padding.end
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(
            textField = "Адреса Елктронної пошти",
            onEmailChange = { mail = it},
            mail = mail
        )

        PasswordField(
            textField = "Адреса Елктронної пошти",
            onEmailChange = { mail = it},
            mail = mail
        )

        Button(
            onClick = {
                navController.navigate(RegistrationScreen.FreePassword.createRoute(mail))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            ),
            modifier = Modifier.padding(
                top = 10.dp
            )
        ) {
            Text(
                text = "Далі",
                color = Color.Black
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    MusicTheme {
        LogIn(
            navController = NavController(LocalContext.current)
        )
    }
}