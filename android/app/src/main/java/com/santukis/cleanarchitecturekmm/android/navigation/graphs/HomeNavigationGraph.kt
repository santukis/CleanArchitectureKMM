package com.santukis.cleanarchitecturekmm.android.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.MoviesDestination
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.ShowsDestination
import com.santukis.viewmodels.events.OnUiEvent
import com.santukis.navigation.graphs.navigate

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