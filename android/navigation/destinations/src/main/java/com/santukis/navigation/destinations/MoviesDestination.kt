package com.santukis.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.*
import com.santukis.injection.getDependencyInjector
import com.santukis.movies.screens.MoviesScreen
import com.santukis.navigation.destination.Destination
import com.santukis.viewmodels.core.entities.MovieSection
import com.santukis.viewmodels.core.events.OnUiEvent

class MoviesDestination(private val section: MovieSection = MovieSection.UpcomingMovies) : Destination {

    override val template: String = "movies/{section}"

    override fun getArguments(): List<NamedNavArgument> =
        listOf(
            navArgument("section") {
                type = NavType.StringType
                defaultValue = MovieSection.UpcomingMovies.value
            }
        )

    @Composable
    override fun DestinationScreen(
        navController: NavController,
        backStackEntry: NavBackStackEntry,
        onUiEvent: (OnUiEvent) -> Unit
    ) {
        MoviesScreen(
            section = MovieSection.from(backStackEntry.arguments?.getString("section").orEmpty()),
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
        navController.navigate("movies/${section.value}", builder)
    }
}