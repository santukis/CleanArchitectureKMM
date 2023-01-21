package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.MoviesDestination
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.MoviesScreenDependencies
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.ShowsDestination
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.ShowsScreenDependencies

fun NavGraphBuilder.homeNavigationGraph(
    navController: NavController
) {
    navigation(
        startDestination = MoviesDestination().template,
        route = NavigationGraph.HOME.name
    ) {
        navigate(
            navController = navController,
            destination = MoviesDestination(),
            screenDependencies = MoviesScreenDependencies()
        )

        navigate(
            navController = navController,
            destination = ShowsDestination(),
            screenDependencies = ShowsScreenDependencies()
        )
    }
}