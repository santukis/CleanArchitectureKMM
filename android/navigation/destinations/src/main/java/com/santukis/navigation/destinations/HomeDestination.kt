package com.santukis.navigation.destinations

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.santukis.home.screens.HomeScreen
import com.santukis.injection.provider.DependencyInjectorProvider
import com.santukis.navigation.destination.DecoratedDestination
import com.santukis.viewmodels.core.events.OnUiEvent

class HomeDestination: DecoratedDestination {

    override val template: String = "home"

    @Composable
    override fun DestinationScreen(
        navController: NavController,
        backStackEntry: NavBackStackEntry,
        onUiEvent: (OnUiEvent) -> Unit
    ) {
        HomeScreen(
            homeViewModel = DependencyInjectorProvider.get()
                .homeViewModel(LocalViewModelStoreOwner.current),
            onUiEvent = onUiEvent
        ) { arguments ->
            AppRouter.getDestination(arguments)?.navigate(navController)
        }
    }

    @Composable
    override fun DestinationLabel() {
        Text(
            text =  stringResource(id = R.string.movies)
        )
    }

    @Composable
    override fun DestinationIcon() {
        Icon(
            imageVector = Icons.Filled.Movie,
            contentDescription = ""
        )
    }

    override fun navigate(navController: NavController, builder: NavOptionsBuilder.() -> Unit) {
        navController.navigate(template, builder)
    }
}