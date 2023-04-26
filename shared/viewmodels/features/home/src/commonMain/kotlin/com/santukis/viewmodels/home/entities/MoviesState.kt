package com.santukis.viewmodels.home.entities

import com.santukis.entities.movies.Movie

data class MoviesState(
    val nowPlayingMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val couldLikeMovies: List<Movie> = emptyList(),
    val errorMessage: String? = null
) {

    fun shouldUpdateData(): Boolean =
        nowPlayingMovies.isEmpty()
                || upcomingMovies.isEmpty()
                || popularMovies.isEmpty()
}
