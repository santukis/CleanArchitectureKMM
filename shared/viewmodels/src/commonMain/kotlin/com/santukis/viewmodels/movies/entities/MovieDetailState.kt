package com.santukis.viewmodels.movies.entities

import com.santukis.entities.movies.Movie

data class MovieDetailState(
    val movie: Movie? = null,
    val errorMessage: String? = null
)
