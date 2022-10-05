package com.santukis.datasources.movies

import com.santukis.datasources.remote.services.MoviesApi
import com.santukis.entities.movies.Movie
import com.santukis.repositories.sources.GetMovieDetailDataSource

class RemoteMovieDataSource(
    private val moviesApi: MoviesApi
): GetMovieDetailDataSource {

    override suspend fun getMovie(movieId: String): Movie {
        return moviesApi.getMovieDetail(movieId).toMovie()
    }
}