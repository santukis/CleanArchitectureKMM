package com.santukis.cleanarchitecturekmm.android.core.entities.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.santukis.cleanarchitecturekmm.android.home.components.HomeScreen
import com.santukis.viewmodels.home.HomeViewModel

class HomeDestination: Destination<HomeScreenDependencies> {

    override val template: String = "home"
    override val route: String = "home"

    @Composable
    override fun DestinationScreen(
        screenDependencies: HomeScreenDependencies,
        navController: NavController,
        backStackEntry: NavBackStackEntry
    ) {
        HomeScreen(
            homeViewModel = screenDependencies.viewModel
        ) { destination ->
            navController.navigate(destination.route)
        }
    }
}

class HomeScreenDependencies(
    val viewModel: HomeViewModel
)