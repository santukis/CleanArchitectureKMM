package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination

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

    @Composable
    fun getNavigationItemName(): String = ""

    fun getNavigationItemIcon(): ImageVector = Icons.Filled.Favorite

    @Composable
    fun RowScope.BottomNavigationItem(
        navController: NavController,
        currentDestination: NavDestination?
    ) {

        BottomNavigationItem(
            icon = {
                Icon(
                    getNavigationItemIcon(),
                    contentDescription = null,
                    tint = Color.White
                ) },
            label = {
                Text(
                    text = getNavigationItemName(),
                    color = Color.White
                ) },
            selected = currentDestination?.hierarchy?.any { it.route == template } == true,
            onClick = {
                navController.navigate(route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}