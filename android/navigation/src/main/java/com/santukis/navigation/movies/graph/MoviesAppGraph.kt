package com.santukis.navigation.movies.graph

import androidx.navigation.NavGraphBuilder
import com.santukis.home.navigation.HomeNavigationGraph
import com.santukis.moviedetail.navigation.MovieDetailNavigationGraph
import com.santukis.movies.navigation.MoviesNavigationGraph
import com.santukis.navigation.NavigationGraph
import com.santukis.navigation.Router

class MoviesAppGraph : NavigationGraph {

    override fun NavGraphBuilder.buildGraph(router: Router) {
        listOf(
            HomeNavigationGraph,
            MovieDetailNavigationGraph,
            MoviesNavigationGraph
        ).forEach { graph ->
            with(graph) {
                buildGraph(router)
            }
        }
    }

    override fun getStartDestination(): String = HomeNavigationGraph.getStartDestination()
}
