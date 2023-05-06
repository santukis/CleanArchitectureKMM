package com.santukis.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.santukis.navigation.destinations.HomeDestination
import com.santukis.navigation.destinations.ShowsDestination
import com.santukis.viewmodels.core.events.OnUiEvent

fun NavGraphBuilder.homeNavigationGraph(
    navController: NavController,
    onUiEvent: (OnUiEvent) -> Unit = {}
) {
    navigation(
        startDestination = HomeDestination().template,
        route = NavigationGraph.HOME.name
    ) {
        navigate(
            navController = navController,
            destination = HomeDestination(),
            onUiEvent = onUiEvent
        )

        navigate(
            navController = navController,
            destination = ShowsDestination(),
            onUiEvent = onUiEvent
        )
    }
}