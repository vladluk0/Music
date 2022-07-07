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
    object FreePassword : RegistrationScreen("main/free/password")
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
        FreeRegistrationMail(
            back = { navController.popBackStack() },
            toFreeRegPassword = { navController.navigate(RegistrationScreen.FreePassword.route) }
        )
    }
}

fun NavGraphBuilder.addFreePassword(
    navController: NavController,
) {
    composable(
        route = RegistrationScreen.FreePassword.route
    ) {
        FreeRegistrationPassword(
            back = {
                navController.popBackStack()
            }
        )
    }
}
