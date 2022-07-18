package com.example.music.ui.screen.main

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.music.ui.authentification.AuthenticationActivity
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Main() {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Button(
            onClick = {
                FirebaseAuth.getInstance().signOut()

                context.startActivity(Intent(context, AuthenticationActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            ),
            modifier = Modifier.padding(
                top = 10.dp
            )
        ) {
            Text(
                text = "Вийти",
                color = Color.Black
            )
        }
    }
}