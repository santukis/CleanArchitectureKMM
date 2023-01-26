package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.Destination

@Composable
fun BottomNavigationAnimatedVisibility(
    navController: NavHostController,
    bottomNavigationDestinations: List<Destination>
) {
    var shouldShowBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    shouldShowBottomBar = bottomNavigationDestinations.any { it.template == navBackStackEntry?.destination?.route }

    AnimatedVisibility(
        visible = shouldShowBottomBar,
        enter = slideInVertically(initialOffsetY = { contentHeight -> contentHeight }),
        exit = slideOutVertically(targetOffsetY = { contentHeight -> contentHeight })
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent
        ) {
            bottomNavigationDestinations.forEach { destination ->
                NavigationItem(
                    destination = destination,
                    navController = navController,
                    currentDestination = navBackStackEntry?.destination,
                    icon = {
                        Icon(
                            imageVector = destination.getDestinationIcon(),
                            contentDescription = "",
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(
                            text = destination.getDestinationName(),
                            color = Color.White
                        )
                    }
                )
            }
        }
    }
}