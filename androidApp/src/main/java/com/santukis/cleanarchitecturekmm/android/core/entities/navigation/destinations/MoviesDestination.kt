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
import androidx.navigation.NavOptionsBuilder
import com.santukis.cleanarchitecturekmm.android.R
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent
import com.santukis.cleanarchitecturekmm.android.movies.screens.MoviesScreen
import com.santukis.injection.getDependencyInjector

class MoviesDestination: Destination {

    override val template: String = "movies"

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
            destination.navigate(navController)
        }
    }

    override fun navigate(navController: NavController, builder: NavOptionsBuilder.() -> Unit) {
        navController.navigate(template, builder)
    }

    @Composable
    override fun getDestinationName(): String = stringResource(id = R.string.movies)

    override fun getDestinationIcon(): ImageVector = Icons.Filled.Movie
}