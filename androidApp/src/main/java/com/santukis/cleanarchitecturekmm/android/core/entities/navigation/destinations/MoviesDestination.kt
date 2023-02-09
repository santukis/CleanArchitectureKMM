package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.santukis.cleanarchitecturekmm.android.R
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent
import com.santukis.cleanarchitecturekmm.android.movies.screens.MoviesScreen
import com.santukis.injection.getDependencyInjector

class MoviesDestination: Destination {

    override val template: String = "movies"
    override val route: String = "movies"

    @Composable
    override fun DestinationScreen(
        navController: NavController,
        backStackEntry: NavBackStackEntry,
        onUiEvent: (OnUiEvent) -> Unit
    ) {
        MoviesScreen(
            moviesViewModel = getDependencyInjector(LocalContext.current.applicationContext)
                .moviesViewModel(LocalViewModelStoreOwner.current),
            onUiEvent = onUiEvent
        ) { destination ->
            navController.navigate(destination.route)
        }
    }

    @Composable
    override fun getDestinationName(): String = stringResource(id = R.string.movies)

    override fun getDestinationIcon(): ImageVector = Icons.Filled.Movie
}