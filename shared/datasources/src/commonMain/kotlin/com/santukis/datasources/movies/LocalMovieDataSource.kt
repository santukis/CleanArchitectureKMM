package com.santukis.datasources.movies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.sources.SaveMovieDetailDataSource

class LocalMovieDataSource(

): SaveMovieDetailDataSource {

    override suspend fun saveMovie(movie: Movie): Movie {
        return movie
    }
}