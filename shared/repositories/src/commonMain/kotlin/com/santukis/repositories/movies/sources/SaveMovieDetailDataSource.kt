package com.santukis.repositories.movies.sources

import com.santukis.entities.movies.Movie

interface SaveMovieDetailDataSource {

    suspend fun saveMovie(movie: Movie): Movie
}