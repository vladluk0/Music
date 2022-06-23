package com.example.music

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.music.di.ApplicationComponent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Home(

) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val currentSelectedItem by navController.currentScreenAsState()
            HomeBottomNavigation(
                selectedNavigation = currentSelectedItem,
                onNavigationSelected = { selected ->
                    Log.d("zxc", selected.route)
                    navController.navigate(selected.route) {
                        launchSingleTop = true
                        restoreState = true

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
            )
        },
    ) { paddingValues ->
        Row(modifier = Modifier.padding(paddingValues)) {
            AppNavigation(

                navController = navController,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun HomeBottomNavigation(
    selectedNavigation: Screen,
    onNavigationSelected: (Screen) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar() {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedNavigation == item.screen,
                onClick = { onNavigationSelected(item.screen) },
                icon = {
                    Icon(painter = painterResource(id = item.image), contentDescription = "")
                },
                label = { Text(text = item.text) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

data class NavigationItem(
    @DrawableRes val image: Int,
    val text: String,
    val screen: Screen
)

val items = listOf(
    NavigationItem(
        R.drawable.ic_baseline_home_24,
        "Головна",
        Screen.Main
    ),
    NavigationItem(
        R.drawable.ic_baseline_search_24,
        "Пошук",
        Screen.Search
    ),
    NavigationItem(
        R.drawable.ic_baseline_library_music_24,
        "Бібліотека",
        Screen.Library
    ),
    NavigationItem(
        R.drawable.ic_baseline_premium_24,
        "Premium",
        Screen.Premium
    )
)

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<Screen> {
    val selectedItem = remember { mutableStateOf<Screen>(Screen.Main) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == Screen.Main.route } -> {
                    selectedItem.value = Screen.Main
                }
                destination.hierarchy.any { it.route == Screen.Library.route } -> {
                    selectedItem.value = Screen.Library
                }
                destination.hierarchy.any { it.route == Screen.Premium.route } -> {
                    selectedItem.value = Screen.Premium
                }
                destination.hierarchy.any { it.route == Screen.Search.route } -> {
                    selectedItem.value = Screen.Search
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}