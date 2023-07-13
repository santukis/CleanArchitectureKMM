package com.santukis.moviedetail.navigation

import androidx.navigation.NavGraphBuilder
import com.santukis.moviedetail.navigation.destinations.MovieDetailDestination
import com.santukis.navigation.NavigationGraph
import com.santukis.navigation.Router
import com.santukis.navigation.navigate

object MovieDetailNavigationGraph : NavigationGraph {

    override fun NavGraphBuilder.buildGraph(router: Router) {
        navigate(
            router = router,
            destination = MovieDetailDestination
        )
    }

    override fun getStartDestination(): String = "MovieDetail"
}