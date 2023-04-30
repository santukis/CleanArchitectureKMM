package com.santukis.cleanarchitecturekmm.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.MovieDetailDestination
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.MoviesDestination
import com.santukis.cleanarchitecturekmm.android.navigation.graphs.NavigationGraph
import com.santukis.cleanarchitecturekmm.android.navigation.graphs.homeNavigationGraph
import com.santukis.navigation.graphs.navigate
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