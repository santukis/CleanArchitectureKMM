package com.santukis.cleanarchitecturekmm.android.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.santukis.cleanarchitecturekmm.android.core.entities.destinations.*
import com.santukis.injection.DependencyInjector

@Composable
fun MovieNavHost(
    dependencyInjector: DependencyInjector,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Destination<*> = HomeDestination()
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.template
    ) {

        navigate(
            navController = navController,
            destination = HomeDestination(),
            screenDependencies = HomeScreenDependencies(
                viewModel = dependencyInjector.homeViewModel()
            )
        )

        navigate(
            navController = navController,
            destination = MovieDetailDestination(),
            screenDependencies = MovieDetailScreenDependencies(
                viewModel = dependencyInjector.movieDetailViewModel()
            )
        )
    }
}

fun <ScreenDependencies> NavGraphBuilder.navigate(
    navController: NavController,
    destination: Destination<ScreenDependencies>,
    screenDependencies: ScreenDependencies
) {
    composable(
        route = destination.template,
        arguments = destination.getArguments(),
        deepLinks = destination.getDeepLinks()
    ) { backStackEntry ->

        destination.DestinationScreen(
            screenDependencies = screenDependencies,
            navController = navController,
            backStackEntry = backStackEntry
        )
    }
}