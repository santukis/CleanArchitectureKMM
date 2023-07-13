package com.santukis.home.navigation

import androidx.navigation.NavGraphBuilder
import com.santukis.home.navigation.destinations.HomeDestination
import com.santukis.navigation.NavigationGraph
import com.santukis.navigation.Router
import com.santukis.navigation.navigate

object HomeNavigationGraph : NavigationGraph {

    override fun NavGraphBuilder.buildGraph(router: Router) {
        navigate(
            router = router,
            destination = HomeDestination
        )
    }

    override fun getStartDestination(): String = "home"
}