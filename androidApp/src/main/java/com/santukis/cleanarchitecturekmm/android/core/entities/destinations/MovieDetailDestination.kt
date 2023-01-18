package com.santukis.cleanarchitecturekmm.android.core.entities.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.santukis.cleanarchitecturekmm.android.movies.components.MovieDetailScreen
import com.santukis.viewmodels.movies.MovieDetailViewModel

class MovieDetailDestination(movieId: Int = -1): Destination<MovieDetailScreenDependencies> {

    override val template: String = "movieDetail/{movieId}"
    override val route: String = "movieDetail/$movieId"

    override fun getArguments(): List<NamedNavArgument> =
        listOf(
            navArgument("movieId") { type = NavType.StringType }
        )

    @Composable
    override fun DestinationScreen(
        screenDependencies: MovieDetailScreenDependencies,
        navController: NavController,
        backStackEntry: NavBackStackEntry
    ) {
        MovieDetailScreen(
            movieDetailViewModel = screenDependencies.viewModel,
            movieId = backStackEntry.arguments?.getString("movieId").orEmpty()
        )
    }
}

class MovieDetailScreenDependencies(
    val viewModel: MovieDetailViewModel
)