package com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.santukis.cleanarchitecturekmm.android.R
import com.santukis.cleanarchitecturekmm.android.movies.components.MoviesScreen
import com.santukis.injection.getDependencyInjector

class ShowsDestination: Destination {

    override val template: String = "tv_shows"
    override val route: String = "tv_shows"

    @Composable
    override fun DestinationScreen(
        navController: NavController,
        backStackEntry: NavBackStackEntry
    ) {
        MoviesScreen(
            moviesViewModel = getDependencyInjector(LocalContext.current.applicationContext)
                .moviesViewModel(LocalViewModelStoreOwner.current)
        ) { destination ->
            navController.navigate(destination.route)
        }
    }

    @Composable
    override fun getNavigationItemName(): String = stringResource(id = R.string.tv_shows)

    override fun getNavigationItemIcon(): ImageVector = Icons.Filled.Tv
}