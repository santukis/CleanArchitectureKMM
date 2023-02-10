package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent

class PopBackStackDestination: Destination {

    override val template: String = ""

    @Composable
    override fun DestinationScreen(
        navController: NavController,
        backStackEntry: NavBackStackEntry,
        onUiEvent: (OnUiEvent) -> Unit) {

    }

    override fun navigate(navController: NavController, builder: NavOptionsBuilder.() -> Unit) {
        navController.popBackStack()
    }
}