package com.santukis.cleanarchitecturekmm.android.core.entities.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink

interface Destination<ScreenDependencies> {

    val template: String
    val route: String

    fun getArguments(): List<NamedNavArgument> = listOf()

    fun getDeepLinks(): List<NavDeepLink> = listOf()

    @Composable
    fun DestinationScreen(
        screenDependencies: ScreenDependencies,
        navController: NavController,
        backStackEntry: NavBackStackEntry
    )
}