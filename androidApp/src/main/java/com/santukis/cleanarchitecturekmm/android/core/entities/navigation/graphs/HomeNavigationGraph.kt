package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.MoviesDestination
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.ShowsDestination
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent

fun NavGraphBuilder.homeNavigationGraph(
    navController: NavController,
    onUiEvent: (OnUiEvent) -> Unit = {}
) {
    navigation(
        startDestination = MoviesDestination().template,
        route = NavigationGraph.HOME.name
    ) {
        navigate(
            navController = navController,
            destination = MoviesDestination(),
            onUiEvent = onUiEvent
        )

        navigate(
            navController = navController,
            destination = ShowsDestination(),
            onUiEvent = onUiEvent
        )
    }
}