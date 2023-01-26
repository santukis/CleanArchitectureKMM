package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent

class PopBackStackDestination: Destination {

    override val template: String = ""
    override val route: String = ""

    @Composable
    override fun DestinationScreen(
        navController: NavController,
        backStackEntry: NavBackStackEntry,
        onUiEvent: (OnUiEvent) -> Unit) {

    }
}