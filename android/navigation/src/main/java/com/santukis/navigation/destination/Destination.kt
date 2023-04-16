package com.santukis.navigation.destination

import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.santukis.viewmodels.events.OnUiEvent

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

    fun navigate(
        navController: NavController,
        builder: NavOptionsBuilder.() -> Unit = {}
    )
}