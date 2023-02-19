package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetPopularMoviesDataSource
import com.santukis.repositories.strategies.RemoteStrategy

class GetPopularMoviesFromRemoteStrategy(
    private val getPopularMoviesFromRemote: GetPopularMoviesDataSource
): RemoteStrategy<Unit, List<Movie>>() {

    override suspend fun loadFromRemote(input: Unit): List<Movie> =
        getPopularMoviesFromRemote.getPopularMovies()

    override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
        output

}