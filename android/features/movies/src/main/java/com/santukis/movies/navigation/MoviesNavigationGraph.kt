package com.santukis.movies.navigation

import androidx.navigation.NavGraphBuilder
import com.santukis.movies.navigation.destinations.MoviesDestination
import com.santukis.navigation.NavigationGraph
import com.santukis.navigation.Router
import com.santukis.navigation.navigate

object MoviesNavigationGraph : NavigationGraph {

    override fun NavGraphBuilder.buildGraph(router: Router) {
        navigate(
            router = router,
            destination = MoviesDestination
        )
    }

    override fun getStartDestination(): String = "Movies"
}