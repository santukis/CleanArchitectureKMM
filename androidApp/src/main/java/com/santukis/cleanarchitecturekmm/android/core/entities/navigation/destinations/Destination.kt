package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.*
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent

interface Destination {

    val template: String

    fun getArguments(): List<NamedNavArgument> = listOf()

    fun getDeepLinks(): List<NavDeepLink> = listOf()

    @Composable
    fun DestinationScreen(
        navController: NavController,
        backStackEntry: NavBackStackEntry,
        onUiEvent: (OnUiEvent) -> Unit
    )

    fun navigate(navController: NavController, builder: NavOptionsBuilder.() -> Unit = {})

    @Composable
    fun getDestinationName(): String = ""

    fun getDestinationIcon(): ImageVector = Icons.Filled.Favorite

}