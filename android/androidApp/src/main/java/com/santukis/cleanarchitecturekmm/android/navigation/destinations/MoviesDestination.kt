package com.santukis.cleanarchitecturekmm.android.navigation.destinations

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.santukis.cleanarchitecturekmm.android.R
import com.santukis.cleanarchitecturekmm.android.navigation.AppRouter
import com.santukis.home.screens.MoviesScreen
import com.santukis.injection.getDependencyInjector
import com.santukis.navigation.destination.DecoratedDestination
import com.santukis.uievents.OnUiEvent

class MoviesDestination: DecoratedDestination {

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
        ) { arguments ->
            AppRouter.getDestination(arguments)?.navigate(navController)
        }
    }

    @Composable
    override fun DestinationLabel() {
        Text(
            text =  stringResource(id = R.string.movies),
            color = Color.White
        )
    }

    @Composable
    override fun DestinationIcon() {
        Icon(
            imageVector = Icons.Filled.Movie,
            contentDescription = "",
            tint = Color.White
        )
    }

    override fun navigate(navController: NavController, builder: NavOptionsBuilder.() -> Unit) {
        navController.navigate(template, builder)
    }
}