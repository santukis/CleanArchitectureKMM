package com.santukis.viewmodels.home.entities

import com.santukis.entities.movies.Movie

data class HomeState(
    val nowPlayingMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val errorMessage: String? = null
)
