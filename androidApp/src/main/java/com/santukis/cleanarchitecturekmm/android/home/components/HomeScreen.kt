package com.santukis.cleanarchitecturekmm.android.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.MovieNavHost
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.MoviesDestination
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.ShowsDestination

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        scaffoldState = rememberScaffoldState(),
        backgroundColor = Color.Black,
        bottomBar = {
            BottomNavigationAnimatedVisibility(
                navController = navController
            )
        }
    ) { paddingValues ->
        MovieNavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController
        )
    }
}

@Composable
fun BottomNavigationAnimatedVisibility(
    navController: NavHostController
) {
    val bottomNavigationDestinations = listOf(MoviesDestination(), ShowsDestination())
    var shouldShowBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    shouldShowBottomBar = bottomNavigationDestinations.any { it.template == navBackStackEntry?.destination?.route }

    AnimatedVisibility(
        visible = shouldShowBottomBar,
        enter = slideInVertically(initialOffsetY = { contentHeight -> contentHeight }),
        exit = slideOutVertically(targetOffsetY = { contentHeight -> contentHeight })
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent
        ) {
            bottomNavigationDestinations.forEach { destination ->
                with(destination) {
                    BottomNavigationItem(
                        navController = navController,
                        currentDestination = navBackStackEntry?.destination
                    )
                }
            }
        }
    }
}