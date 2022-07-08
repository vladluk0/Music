package com.example.music.ui.authentification

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.music.Screen
import com.example.music.appComponent
import com.example.music.ui.authentification.screen.free_registration.FreeRegistrationMail
import com.example.music.ui.authentification.screen.free_registration.FreeRegistrationPassword
import com.example.music.ui.authentification.screen.log_in.LogIn
import com.example.music.ui.authentification.screen.main.AuthMain
import com.example.music.ui.authentification.screen.main.AuthViewModel

sealed class RegistrationScreen(val route: String) {

    open fun createRoute(root: RegistrationScreen) = "${root.route}/$route"

    object Main : RegistrationScreen("main")

    object FreeMail : RegistrationScreen("main/free")

    object FreePassword : RegistrationScreen("main/free/mail/{mail}/password") {
        fun createRoute(mail: String): String {
            return "main/free/mail/$mail/password"
        }
    }

    object LogIn : RegistrationScreen("main/login")
}

@Composable
fun RegistrationNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = RegistrationScreen.Main.route
    ) {
        addMain(navController)
        addFreeMail(navController)
        addFreePassword(navController)
        addLogin(navController)
    }
}

fun NavGraphBuilder.addMain(
    navController: NavController
) {
    composable(
        route = Screen.Main.route
    ) {
        val context = LocalContext.current
        val registrationComponent = context.registrationComponent
        checkNotNull(registrationComponent) { "registrationComponent null" }
        val viewModel = registrationComponent.factory.create(AuthViewModel::class.java)
        AuthMain(navController, viewModel)
    }
}

fun NavGraphBuilder.addFreeMail(
    navController: NavController,
) {
    composable(
        route = RegistrationScreen.FreeMail.route
    ) {
        FreeRegistrationMail(navController)
    }
}

fun NavGraphBuilder.addFreePassword(
    navController: NavController,
) {
    composable(
        route = RegistrationScreen.FreePassword.route
    ) { backStackEntry ->
        val mail = backStackEntry.arguments?.getString("mail")
        checkNotNull(mail) { "mail in null" }
        FreeRegistrationPassword(navController, mail)
    }
}

fun NavGraphBuilder.addLogin(
    navController: NavController,
) {
    composable(
        route = RegistrationScreen.LogIn.route
    ) {
        LogIn(navController = navController)
    }
}
