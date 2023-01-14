package com.santukis.datasources.movies.local

import com.santukis.datasources.core.local.MovieDatabase
import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.SaveMovieDetailDataSource

class LocalMovieDataSource(
    private val database: MovieDatabase
): SaveMovieDetailDataSource {

    override suspend fun saveMovie(movie: Movie): Movie {
        return movie
    }
}