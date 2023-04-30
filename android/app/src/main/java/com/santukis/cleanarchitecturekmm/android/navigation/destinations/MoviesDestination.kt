package com.santukis.cleanarchitecturekmm.android.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.*
import com.santukis.cleanarchitecturekmm.android.navigation.AppRouter
import com.santukis.injection.getDependencyInjector
import com.santukis.movies.screens.MoviesScreen
import com.santukis.navigation.destination.Destination
import com.santukis.viewmodels.core.events.OnUiEvent

class MoviesDestination(): Destination {

    override val template: String = "movies"

    override fun getArguments(): List<NamedNavArgument> =
        listOf(

        )

    @Composable
    override fun DestinationScreen(
        navController: NavController,
        backStackEntry: NavBackStackEntry,
        onUiEvent: (OnUiEvent) -> Unit
    ) {
        MoviesScreen(
            moviesViewModel = getDependencyInjector()
                .moviesViewModel(LocalViewModelStoreOwner.current),
            onUiEvent = onUiEvent
        ) { arguments ->
            AppRouter.getDestination(arguments)?.navigate(navController)
        }
    }

    override fun navigate(
        navController: NavController,
        builder: NavOptionsBuilder.() -> Unit
    ) {
        navController.navigate(template, builder)
    }
}