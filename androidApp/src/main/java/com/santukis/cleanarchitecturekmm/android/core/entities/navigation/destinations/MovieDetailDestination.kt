package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.*
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent
import com.santukis.cleanarchitecturekmm.android.movies.screens.MovieDetailScreen
import com.santukis.injection.getDependencyInjector

class MovieDetailDestination(movieId: Int = -1): Destination {

    override val template: String = "movieDetail/{movieId}"
    override val route: String = "movieDetail/$movieId"

    override fun getArguments(): List<NamedNavArgument> =
        listOf(
            navArgument("movieId") { type = NavType.StringType }
        )

    @Composable
    override fun DestinationScreen(
        navController: NavController,
        backStackEntry: NavBackStackEntry,
        onUiEvent: (OnUiEvent) -> Unit
    ) {
        MovieDetailScreen(
            movieDetailViewModel = getDependencyInjector(LocalContext.current.applicationContext)
                .movieDetailViewModel(LocalViewModelStoreOwner.current),
            movieId = backStackEntry.arguments?.getString("movieId").orEmpty(),
            onUiEvent = onUiEvent
        ) { destination ->
            if (destination is PopBackStackDestination) {
                navController.popBackStack()

            } else {
                navController.navigate(destination.route)
            }
        }
    }
}