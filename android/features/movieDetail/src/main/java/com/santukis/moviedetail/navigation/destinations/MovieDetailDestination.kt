package com.santukis.moviedetail.navigation.destinations

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.*
import com.santukis.injection.provider.DependencyInjectorProvider
import com.santukis.moviedetail.screens.MovieDetailScreen
import com.santukis.navigation.PopBackStack
import com.santukis.navigation.Router
import com.santukis.navigation.ScreenDestination

object MovieDetailDestination: ScreenDestination {

    private const val movieIdKey = "movieId"
    private const val host = "https://santukis.com/"

    override val template: String = "movieDetail/{$movieIdKey}"

    override fun getArguments(): List<NamedNavArgument> =
        listOf(
            navArgument(movieIdKey) {
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
        router: Router,
        backStackEntry: NavBackStackEntry
    ) {
        MovieDetailScreen(
            movieDetailViewModel = DependencyInjectorProvider.get()
                .movieDetailViewModel(LocalViewModelStoreOwner.current),
            movieId = backStackEntry.arguments?.getString(movieIdKey).orEmpty()
        ) { arguments ->
            when (arguments) {
                is PopBackStack -> router.popBackStack()
            }
        }
    }
}
