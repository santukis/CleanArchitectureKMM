package com.santukis.cleanarchitecturekmm.android.navigation

import com.santukis.cleanarchitecturekmm.android.navigation.destinations.MovieDetailDestination
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.MoviesDestination
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.PopBackStackDestination
import com.santukis.navigation.destination.Destination
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.navigation.destination.arguments.MovieDetailDestinationArguments
import com.santukis.navigation.destination.arguments.MoviesDestinationArguments
import com.santukis.navigation.destination.arguments.PopBackStackDestinationArguments

object AppRouter {

    fun getDestination(arguments: DestinationArguments): Destination? =
        when (arguments) {
            is MovieDetailDestinationArguments -> MovieDetailDestination(movieId = arguments.movieId)
            is MoviesDestinationArguments -> MoviesDestination(section = arguments.section)
            is PopBackStackDestinationArguments -> PopBackStackDestination()
            else -> null
        }
}