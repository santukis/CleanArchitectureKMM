package com.santukis.home.navigation.arguments

import com.santukis.navigation.DestinationArguments

data class ToMovieDetailDestinationArguments(
    val movieId: Int
): DestinationArguments {

    override fun getRoute(): String = "movieDetail/$movieId"
}