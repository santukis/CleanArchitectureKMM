package com.santukis.home.navigation.arguments

import com.santukis.navigation.DestinationArguments
import com.santukis.viewmodels.core.entities.MovieSection

class ToMoviesDestinationArguments(val section: MovieSection) : DestinationArguments {

    override fun getRoute(): String = "movies/${section.value}"
}