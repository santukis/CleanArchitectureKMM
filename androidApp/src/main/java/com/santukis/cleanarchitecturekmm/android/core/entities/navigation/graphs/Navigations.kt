package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.Destination

enum class NavigationGraph {
    HOME
}

fun <ScreenDependencies> NavGraphBuilder.navigate(
    navController: NavController,
    destination: Destination<ScreenDependencies>,
    screenDependencies: ScreenDependencies
) {
    composable(
        route = destination.template,
        arguments = destination.getArguments(),
        deepLinks = destination.getDeepLinks()
    ) { backStackEntry ->

        destination.DestinationScreen(
            screenDependencies = screenDependencies,
            navController = navController,
            backStackEntry = backStackEntry
        )
    }
}