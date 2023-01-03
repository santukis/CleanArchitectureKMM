package com.santukis.viewmodels.movies.entities

import com.santukis.entities.movies.Movie

data class HighlightMovieState(
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null
)
