package com.example.music

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.music.di.DaggerScreenComponent
import com.example.music.ui.screen.add_artist.AddArtist
import com.example.music.ui.screen.add_artist.AddArtistVM
import com.example.music.ui.screen.library.Library
import com.example.music.ui.screen.library.LibraryVM
import com.example.music.ui.screen.main.Main
import com.example.music.ui.screen.premium.Premium
import com.example.music.ui.screen.search.Search

sealed class Screen(
    val route: String
) {
    object Main : Screen("main")
    object Search : Screen("search")
    object Library : Screen("library")
    object Premium : Screen("premium")
}

private sealed class LeafScreen(
    private val route: String
) {
    open fun createRoute(root: Screen) = "${root.route}/$route"

    object Main : LeafScreen("main")
    object Search : LeafScreen("search")
    object Library : LeafScreen("library")
    object Premium : LeafScreen("premium")

    object AddNewArtist: LeafScreen("artistlist") {
        override fun createRoute(root: Screen): String {
            return "${root.route}/artistlist"
        }
    }
}

@ExperimentalAnimationApi
@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier,
    ) {
        addMainTopLevel(navController)
        addSearchTopLevel(navController)
        addLibraryTopLevel(navController)
        addPremiumTopLevel(navController)
    }
}

fun NavGraphBuilder.addMainTopLevel(
    navController: NavController,
) {
    navigation(
        route = Screen.Main.route,
        startDestination = LeafScreen.Main.createRoute(Screen.Main)
    ) {
        addMain(navController, Screen.Main)
    }
}

fun NavGraphBuilder.addLibraryTopLevel(
    navController: NavController,
) {
    navigation(
        route = Screen.Library.route,
        startDestination = LeafScreen.Library.createRoute(Screen.Library)
    ) {
        addLibrary(navController, Screen.Library)
        addArtistList(navController, Screen.Library)
    }
}

fun NavGraphBuilder.addSearchTopLevel(
    navController: NavController,
) {
    navigation(
        route = Screen.Search.route,
        startDestination = LeafScreen.Search.createRoute(Screen.Search)
    ) {
        addSearch(navController, Screen.Search)
    }
}

fun NavGraphBuilder.addPremiumTopLevel(
    navController: NavController,
) {
    navigation(
        route = Screen.Premium.route,
        startDestination = LeafScreen.Premium.createRoute(Screen.Premium)
    ) {
        addPremium(navController, Screen.Premium)
    }
}

fun NavGraphBuilder.addMain(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.Main.createRoute(root),
    ) {
        Main()
    }
}

fun NavGraphBuilder.addLibrary(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.Library.createRoute(root),
    ) {
        val screenComponent = DaggerScreenComponent.factory().create(LocalContext.current.appComponent)
        val viewModel = screenComponent.factory.create(LibraryVM::class.java)
        Library(
            viewModel = viewModel,
            addNewArtist = {
                navController.navigate(LeafScreen.AddNewArtist.createRoute(root))
            }
        )
    }
}

fun NavGraphBuilder.addSearch(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.Search.createRoute(root),
    ) {
        Search()
    }
}

fun NavGraphBuilder.addPremium(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.Premium.createRoute(root),
    ) {
        Premium()
    }
}

fun NavGraphBuilder.addArtistList(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.AddNewArtist.createRoute(root),
    ) {
        val screenComponent = DaggerScreenComponent.factory().create(LocalContext.current.appComponent)
        val viewModel = screenComponent.factory.create(AddArtistVM::class.java)
        AddArtist(viewModel)
    }
}

/*fun NavGraphBuilder.addNewArtist(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.Library.createRoute(root),
    ) {
        AddNewArtist(
            addNewArtist = {
                navController.navigate(LeafScreen.AddNewArtist.createRoute(root))
            }
        )
    }
}*/

