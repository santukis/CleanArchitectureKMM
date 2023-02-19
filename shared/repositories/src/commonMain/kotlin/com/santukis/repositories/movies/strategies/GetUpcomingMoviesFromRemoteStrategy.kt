package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetNowPlayingMoviesDataSource
import com.santukis.repositories.movies.sources.GetUpcomingMoviesDataSource
import com.santukis.repositories.strategies.RemoteStrategy

class GetUpcomingMoviesFromRemoteStrategy(
    private val getUpcomingMoviesFromRemote: GetUpcomingMoviesDataSource
): RemoteStrategy<Unit, List<Movie>>() {

    override suspend fun loadFromRemote(input: Unit): List<Movie> =
        getUpcomingMoviesFromRemote.getUpcomingMovies()

    override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
        output

}