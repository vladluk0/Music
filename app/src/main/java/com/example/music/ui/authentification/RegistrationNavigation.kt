package com.example.music.ui.authentification

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.music.Screen
import com.example.music.ui.authentification.screen.free_registration.FreeRegistrationMail
import com.example.music.ui.authentification.screen.free_registration.FreeRegistrationPassword
import com.example.music.ui.authentification.screen.main.AuthMain

sealed class RegistrationScreen(val route: String) {

    open fun createRoute(root: RegistrationScreen) = "${root.route}/$route"

    object Main : RegistrationScreen("main")

    object FreeMail : RegistrationScreen("main/free")

    object FreePassword : RegistrationScreen("main/free/mail/{mail}/password") {
        fun createRoute(mail: String): String {
            return "main/free/mail/$mail/password"
        }
    }
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
    }
}

fun NavGraphBuilder.addMain(
    navController: NavController
) {
    composable(
        route = Screen.Main.route
    ) {
        AuthMain(
            toFreeRegistration = {
                navController.navigate(RegistrationScreen.FreeMail.route)
            }
        )
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
