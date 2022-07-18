package com.example.music.ui.common.field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.music.ui.authentification.screen.free_registration.FreeRegistrationPassword
import com.example.music.ui.theme.MusicTheme
import com.example.music.ui.theme.padding

@Composable
fun EmailField(
    modifier: Modifier = Modifier,
    textField: String,
    onEmailChange: (String) -> Unit,
    mail: String
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(
            start = MaterialTheme.padding.start,
            end = MaterialTheme.padding.end
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = textField,
            fontSize = 30.sp,
            color = Color.White,
            modifier = modifier,
            lineHeight = 38.sp
        )

        TextField(
            value = mail,
            onValueChange = {
                onEmailChange.invoke(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .scale(scaleY = 0.8F, scaleX = 1F),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Gray
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            maxLines = 1
        )
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
