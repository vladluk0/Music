package com.example.music.ui.authentification

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.music.ui.authentification.screen.main.AuthMain
import com.example.music.ui.theme.MusicTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationActivity: ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        setContent {
            MusicTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colorScheme.background
                ) {
                    RegistrationHome()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    MusicTheme {

    }
}
