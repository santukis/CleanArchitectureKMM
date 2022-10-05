package com.santukis.repositories.sources

import com.santukis.entities.movies.Movie

interface SaveMovieDetailDataSource {

    suspend fun saveMovie(movie: Movie): Movie
}