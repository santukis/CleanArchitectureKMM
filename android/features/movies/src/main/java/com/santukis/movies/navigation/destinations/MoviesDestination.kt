package com.santukis.movies.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.*
import com.santukis.injection.provider.DependencyInjectorProvider
import com.santukis.movies.screens.MoviesScreen
import com.santukis.navigation.DestinationArguments
import com.santukis.navigation.PopBackStack
import com.santukis.navigation.Router
import com.santukis.navigation.ScreenDestination
import com.santukis.viewmodels.core.entities.MovieSection

object MoviesDestination : ScreenDestination {

    private const val sectionKey = "section"

    override val template: String = "movies/{$sectionKey}"

    override fun getArguments(): List<NamedNavArgument> =
        listOf(
            navArgument(sectionKey) {
                type = NavType.StringType
                defaultValue = MovieSection.UpcomingMovies.value
            }
        )

    @Composable
    override fun DestinationScreen(
        router: Router,
        backStackEntry: NavBackStackEntry) {
        MoviesScreen(
            section = MovieSection.from(backStackEntry.arguments?.getString(sectionKey).orEmpty()),
            moviesViewModel = DependencyInjectorProvider.get()
                .moviesViewModel(LocalViewModelStoreOwner.current)
        ) { arguments ->
            when (arguments) {
                is PopBackStack -> router.popBackStack()
                is DestinationArguments -> router.navigate(arguments)
            }
        }
    }
}
