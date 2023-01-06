package com.santukis.datasources.movies.local

import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.SaveMovieDetailDataSource

class LocalMovieDataSource(

): SaveMovieDetailDataSource {

    override suspend fun saveMovie(movie: Movie): Movie {
        return movie
    }
}