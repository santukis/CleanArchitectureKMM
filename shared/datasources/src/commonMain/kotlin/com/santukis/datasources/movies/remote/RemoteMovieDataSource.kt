package com.santukis.datasources.movies.remote

import com.santukis.datasources.movies.remote.services.MoviesApi
import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetMovieDetailDataSource

class RemoteMovieDataSource(
    private val moviesApi: MoviesApi
): GetMovieDetailDataSource {

    override suspend fun getMovie(movieId: String): Movie {
        return moviesApi.getMovieDetail(movieId).toMovie()
    }
}