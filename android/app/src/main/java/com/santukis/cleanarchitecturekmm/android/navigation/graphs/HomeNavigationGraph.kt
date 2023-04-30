package com.santukis.cleanarchitecturekmm.android.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.HomeDestination
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.ShowsDestination
import com.santukis.navigation.graphs.navigate
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