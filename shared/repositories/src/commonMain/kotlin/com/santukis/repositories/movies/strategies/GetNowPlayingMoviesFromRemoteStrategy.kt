package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetNowPlayingMoviesDataSource
import com.santukis.repositories.strategies.RemoteStrategy

class GetNowPlayingMoviesFromRemoteStrategy(
    private val getNowPlayingMoviesFromRemote: GetNowPlayingMoviesDataSource
): RemoteStrategy<Unit, List<Movie>>() {

    override suspend fun loadFromRemote(input: Unit): List<Movie> =
        getNowPlayingMoviesFromRemote.getNowPlayingMovies()

    override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
        output

}