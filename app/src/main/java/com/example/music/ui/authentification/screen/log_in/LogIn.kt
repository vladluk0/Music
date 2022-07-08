package com.example.music.ui.authentification.screen.log_in

import android.content.Intent
import android.util.Log
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
import com.example.music.ui.common.field.EmailField
import com.example.music.ui.common.field.PasswordField
import com.example.music.ui.common.top_bar.SimpleAppBar
import com.example.music.ui.theme.MusicTheme
import com.example.music.ui.theme.padding
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogIn(
    navController: NavController
) {
    Scaffold(
        topBar = {
            SimpleAppBar(
                navController,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            LogInContent()
        }
    }
}

@Composable
fun LogInContent(

) {
    var mail by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(
            start = MaterialTheme.padding.start,
            end = MaterialTheme.padding.end
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(
            modifier = Modifier.padding(
                top = 50.dp
            ),
            textField = "Адреса Електронної пошти",
            onEmailChange = { mail = it},
            mail = mail
        )

        PasswordField(
            modifier = Modifier.padding(
                top = 30.dp
            ),
            textField = "Пароль",
            onEmailChange = { password = it},
            password = password
        )

        Button(
            onClick = {
                Log.d("zxc", "$mail $password")
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    mail,
                    password
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("zxc", "success")
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
                text = "Увійти",
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