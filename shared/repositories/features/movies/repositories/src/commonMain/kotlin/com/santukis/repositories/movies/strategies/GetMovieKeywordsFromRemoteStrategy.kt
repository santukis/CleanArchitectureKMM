package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Keyword
import com.santukis.repositories.movies.sources.GetKeywordsForMovieDataSource
import com.santukis.repositories.movies.sources.SaveMovieKeywordsDataSource
import com.santukis.repositories.strategies.RemoteStrategy

class GetMovieKeywordsFromRemoteStrategy(
    private val getMovieKeywordsFromRemote: GetKeywordsForMovieDataSource,
    private val saveMovieKeywordsToLocal: SaveMovieKeywordsDataSource,
): RemoteStrategy<String, List<Keyword>>() {

    private var movieId: String = ""

    override suspend fun loadFromRemote(input: String): List<Keyword> =
        getMovieKeywordsFromRemote.getKeywordsForMovie(input)

    override suspend fun saveIntoLocal(output: List<Keyword>): List<Keyword> {
        saveMovieKeywordsToLocal.saveMovieKeywords(movieId, output)
        return output
    }

    override suspend fun execute(input: String): List<Keyword> {
        movieId = input
        return super.execute(input)
    }
}