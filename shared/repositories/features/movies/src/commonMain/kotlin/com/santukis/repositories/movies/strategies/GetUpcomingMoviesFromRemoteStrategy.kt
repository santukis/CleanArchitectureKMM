package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetUpcomingMoviesDataSource
import com.santukis.repositories.strategies.RemoteStrategy

class GetUpcomingMoviesFromRemoteStrategy(
    private val getUpcomingMoviesFromRemote: GetUpcomingMoviesDataSource
): RemoteStrategy<Int, List<Movie>>() {

    override suspend fun loadFromRemote(input: Int): List<Movie> =
        getUpcomingMoviesFromRemote.getUpcomingMovies(input)

    override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
        output

}