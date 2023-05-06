package com.santukis.navigation.bottombar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.santukis.navigation.destination.Destination


@Composable
fun RowScope.NavigationItem(
    destination: Destination,
    navController: NavController,
    currentDestination: NavDestination?,
    icon: @Composable () -> Unit = {},
    label: @Composable () -> Unit = {}
) {
    BottomNavigationItem(
        icon = icon,
        label = label,
        selected = currentDestination?.hierarchy?.any { it.route == destination.template } == true,
        onClick = {
            destination.navigate(navController) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}