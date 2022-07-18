package com.example.music.ui.authentification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.music.MainActivity
import com.example.music.MusicApplication
import com.example.music.R
import com.example.music.appComponent
import com.example.music.di.ApplicationComponent
import com.example.music.di.registration.RegistrationComponent
import com.example.music.ui.authentification.screen.main.AuthMain
import com.example.music.ui.theme.MusicTheme
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AuthenticationActivity : ComponentActivity() {

    @Inject
    lateinit var auth: FirebaseAuth

    lateinit var registrationComponent: RegistrationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        registrationComponent = appComponent.registrationComponent().bindContext(this).build()
        registrationComponent.inject(this)
        super.onCreate(savedInstanceState)

        checkIfAuth()

        setContent {
            MusicTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    RegistrationHome()
                }
            }
        }
    }

    fun checkIfAuth() {
        if (auth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
            finish()
        }
    }
}

internal val Context.registrationComponent: RegistrationComponent?
    get() = when (this) {
        is AuthenticationActivity -> registrationComponent
        else -> null
    }

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    MusicTheme {

    }
}
