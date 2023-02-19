package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Keyword
import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetMoviesByKeywordDataSource
import com.santukis.repositories.strategies.RemoteStrategy

class GetMoviesByKeywordFromRemoteStrategy(
    private val getMoviesByKeywordFromRemote: GetMoviesByKeywordDataSource,
): RemoteStrategy<List<Keyword>, List<Movie>>() {

    override suspend fun loadFromRemote(input: List<Keyword>): List<Movie> =
        getMoviesByKeywordFromRemote.getMoviesByKeyword(input)

    override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
        output
}