package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetMovieDetailDataSource
import com.santukis.repositories.movies.sources.SaveMovieDetailDataSource
import com.santukis.repositories.strategies.RemoteStrategy

class GetMovieDetailFromRemoteStrategy(
    private val getMovieDetailFromRemote: GetMovieDetailDataSource,
    private val saveMovieDetailToLocal: SaveMovieDetailDataSource,
): RemoteStrategy<String, Movie>() {

    override suspend fun loadFromRemote(input: String): Movie =
        getMovieDetailFromRemote.getMovie(input)

    override suspend fun saveIntoLocal(output: Movie): Movie =
        saveMovieDetailToLocal.saveMovie(output)
}