package com.santukis.cleanarchitecturekmm.android.navigation.destinations

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.*
import com.santukis.cleanarchitecturekmm.android.navigation.AppRouter
import com.santukis.moviedetail.screens.MovieDetailScreen
import com.santukis.injection.getDependencyInjector
import com.santukis.navigation.destination.Destination
import com.santukis.viewmodels.events.OnUiEvent

class MovieDetailDestination(private val movieId: Int = -1): Destination {

    private val host = "https://santukis.com/"

    override val template: String = "movieDetail/{movieId}"

    override fun getArguments(): List<NamedNavArgument> =
        listOf(
            navArgument("movieId") {
                type = NavType.StringType
            }
        )

    override fun getDeepLinks(): List<NavDeepLink> =
        listOf(
            navDeepLink {
                uriPattern = "$host$template"
                action = Intent.ACTION_VIEW
            }
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
        ) { arguments ->
            AppRouter.getDestination(arguments)?.navigate(navController)
        }
    }

    override fun navigate(
        navController: NavController,
        builder: NavOptionsBuilder.() -> Unit
    ) {
        navController.navigate("movieDetail/$movieId", builder)
    }
}