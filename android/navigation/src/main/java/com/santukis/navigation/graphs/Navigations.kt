package com.santukis.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.santukis.navigation.destination.Destination
import com.santukis.uievents.OnUiEvent

fun NavGraphBuilder.navigate(
    navController: NavController,
    destination: Destination,
    onUiEvent: (OnUiEvent) -> Unit = {}
) {
    composable(
        route = destination.template,
        arguments = destination.getArguments(),
        deepLinks = destination.getDeepLinks()
    ) { backStackEntry ->

        destination.DestinationScreen(
            navController = navController,
            backStackEntry = backStackEntry,
            onUiEvent = onUiEvent
        )
    }
}