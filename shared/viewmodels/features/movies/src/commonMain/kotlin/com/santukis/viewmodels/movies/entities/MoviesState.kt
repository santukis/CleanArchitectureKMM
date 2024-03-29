package com.santukis.viewmodels.movies.entities

import com.santukis.entities.movies.Movie

data class MoviesState(
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null
) {

    fun addMovies(movies: List<Movie>): MoviesState =
        copy(movies = this.movies + movies)
}
