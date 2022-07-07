package com.example.music.ui.authentification.screen.free_registration

import android.icu.text.UnicodeSetIterator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.music.ui.authentification.RegistrationScreen
import com.example.music.ui.common.field.EmailField
import com.example.music.ui.common.top_bar.SimpleAppBar
import com.example.music.ui.theme.MusicTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeRegistrationMail(
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
            FreeMailContent(navController)
        }
    }
}

@Composable
fun FreeMailContent(
    navController: NavController
) {
    var mail by remember {
        mutableStateOf("")
    }

    Column {
        EmailField(
            textField = "Укажіть адресу електронної пошти",
            onEmailChange = {
                mail = it
            },
            mail = mail
        )

        ButtonNext(
            onNextClick = {
                navController.navigate(RegistrationScreen.FreePassword.createRoute(mail))
            },
        )
    }
}

@Composable
fun ButtonNext(
    onNextClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                onNextClick.invoke()
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
        FreeRegistrationMail(
            navController = NavController(LocalContext.current)
        )
    }
}
