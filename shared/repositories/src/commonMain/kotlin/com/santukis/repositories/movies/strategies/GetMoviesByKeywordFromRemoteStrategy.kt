package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.entities.GetMoviesByKeywordRequest
import com.santukis.repositories.movies.sources.GetMoviesByKeywordDataSource
import com.santukis.repositories.strategies.RemoteStrategy

class GetMoviesByKeywordFromRemoteStrategy(
    private val getMoviesByKeywordFromRemote: GetMoviesByKeywordDataSource,
): RemoteStrategy<GetMoviesByKeywordRequest, List<Movie>>() {

    override suspend fun loadFromRemote(input: GetMoviesByKeywordRequest): List<Movie> =
        getMoviesByKeywordFromRemote.getMoviesByKeyword(input.keywords, input.page)

    override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
        output
}