package com.santukis.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.santukis.navigation.destinations.MovieDetailDestination
import com.santukis.navigation.destinations.MoviesDestination
import com.santukis.viewmodels.core.events.OnUiEvent

@Composable
fun MovieNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onUiEvent: (OnUiEvent) -> Unit = {}
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationGraph.HOME.name
    ) {

        homeNavigationGraph(
            navController = navController,
            onUiEvent = onUiEvent
        )

        navigate(
            navController = navController,
            destination = MovieDetailDestination(),
            onUiEvent = onUiEvent
        )

        navigate(
            navController = navController,
            destination = MoviesDestination(),
            onUiEvent = onUiEvent
        )
    }
}