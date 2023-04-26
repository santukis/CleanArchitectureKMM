package com.santukis.viewmodels.moviedetail.entities

import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.Video

data class MovieDetailState(
    val movie: Movie? = null,
    val videos: List<Video> = emptyList(),
    val errorMessage: String? = null
)
