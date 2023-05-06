package com.santukis.navigation.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.santukis.navigation.destination.DecoratedDestination

@Composable
fun BottomNavigationAnimatedVisibility(
    navController: NavHostController,
    bottomNavigationDestinations: List<DecoratedDestination>
) {
    var shouldShowBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    shouldShowBottomBar = bottomNavigationDestinations.any { it.template == navBackStackEntry?.destination?.route }

    AnimatedVisibility(
        visible = shouldShowBottomBar,
        enter = slideInVertically(initialOffsetY = { contentHeight -> contentHeight }),
        exit = slideOutVertically(targetOffsetY = { contentHeight -> contentHeight })
    ) {
        BottomNavigation() {
            bottomNavigationDestinations.forEach { destination ->
                NavigationItem(
                    destination = destination,
                    navController = navController,
                    currentDestination = navBackStackEntry?.destination,
                    icon = {
                        destination.DestinationIcon()
                    },
                    label = {
                        destination.DestinationLabel()
                    }
                )
            }
        }
    }
}