package com.santukis.home.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import com.santukis.home.screens.HomeScreen
import com.santukis.injection.provider.DependencyInjectorProvider
import com.santukis.navigation.DestinationArguments
import com.santukis.navigation.PopBackStack
import com.santukis.navigation.Router
import com.santukis.navigation.ScreenDestination

object HomeDestination: ScreenDestination {

    override val template: String = "home"

    @Composable
    override fun DestinationScreen(
        router: Router,
        backStackEntry: NavBackStackEntry
    ) {
        HomeScreen(
            homeViewModel = DependencyInjectorProvider.get()
                .homeViewModel(LocalViewModelStoreOwner.current)
        ) { arguments ->
            when (arguments) {
                is PopBackStack -> router.popBackStack()
                is DestinationArguments -> router.navigate(arguments)
            }
        }
    }
}