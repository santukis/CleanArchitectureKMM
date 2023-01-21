package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.Destination

enum class NavigationGraph {
    HOME, USER
}

fun NavGraphBuilder.navigate(
    navController: NavController,
    destination: Destination
) {
    composable(
        route = destination.template,
        arguments = destination.getArguments(),
        deepLinks = destination.getDeepLinks()
    ) { backStackEntry ->

        destination.DestinationScreen(
            navController = navController,
            backStackEntry = backStackEntry
        )
    }
}