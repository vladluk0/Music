package com.example.music.ui.authentification.screen.free_registration

import android.content.Intent
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
import com.example.music.MainActivity
import com.example.music.ui.common.field.PasswordField
import com.example.music.ui.common.top_bar.SimpleAppBar
import com.example.music.ui.theme.MusicTheme
import com.example.music.ui.theme.padding
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeRegistrationPassword(
    navController: NavController,
    email: String
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
            FreeRegContent(
                email = email
            )
        }
    }
}

@Composable
fun FreeRegContent(
    email: String
) {

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(
            start = MaterialTheme.padding.start,
            end = MaterialTheme.padding.end
        ),
    ) {
        PasswordField(
            modifier = Modifier.padding(
                top = 50.dp
            ),
            textField = "Створіть пароль",
            onEmailChange = {
                password = it
            },
            password = password
        )

        CreateAccountButton(
            email = email,
            password = password
        )
    }
}

@Composable
fun CreateAccountButton(
    email: String,
    password: String
) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    email,
                    password
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
                }
            },
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
        FreeRegistrationPassword(
            navController = NavController(LocalContext.current),
            email = ""
        )
    }
}
