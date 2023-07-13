package com.santukis.cleanarchitecturekmm.android.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.santukis.navigation.BottomNavigationDestinationArguments
import com.santukis.navigation.Router

@Composable
fun MoviesBottomNavigation(
    router: Router,
    destinationArguments: List<BottomNavigationDestinationArguments>
) {
    var shouldShowBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by router.navController.currentBackStackEntryAsState()

    shouldShowBottomBar = destinationArguments.any {
        it.getRoute() == navBackStackEntry?.destination?.route
    }

    AnimatedVisibility(
        visible = shouldShowBottomBar,
        enter = slideInVertically(initialOffsetY = { contentHeight -> contentHeight }),
        exit = slideOutVertically(targetOffsetY = { contentHeight -> contentHeight })
    ) {
        BottomNavigation {
            destinationArguments.forEach { destinationArgument ->
                BottomNavigationItem(
                    icon = { destinationArgument.BottomNavigationItemIcon() },
                    label = { destinationArgument.BottomNavigationItemTitle() },
                    selected = navBackStackEntry?.destination?.hierarchy
                        ?.any { it.route == destinationArgument.getRoute() } == true,
                    onClick = {
                        router.navigate(destinationArgument) {
                            popUpTo(router.navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}