package com.santukis.datasources.movies.local

import com.santukis.datasources.local.MovieDatabase
import com.santukis.entities.movies.Keyword
import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetMostFrequentlyKeywordsDataSource
import com.santukis.repositories.movies.sources.SaveMovieDetailDataSource
import com.santukis.repositories.movies.sources.SaveMovieKeywordsDataSource

class LocalMovieDataSource(
    private val database: MovieDatabase
) :
    SaveMovieDetailDataSource,
    SaveMovieKeywordsDataSource,
    GetMostFrequentlyKeywordsDataSource {

    override suspend fun saveMovie(movie: Movie): Movie {
        return movie
    }

    override suspend fun saveMovieKeywords(movieId: String, keywords: List<Keyword>) {
        database.keywordsQueries.transaction {
            keywords.forEach { keyword ->
                updateMovieKeyword(keyword)
            }
        }
    }

    override suspend fun getMostFrequentlyKeywords(): List<Keyword> {
        return database.keywordsQueries
            .selectFrequentlyUsedKeywords()
            .executeAsList()
            .map { keyword ->
                Keyword(
                    id = keyword.id.toInt(),
                    name = keyword.name
                )
            }
    }

    private fun updateMovieKeyword(keyword: Keyword) {
        database.keywordsQueries
            .selectKeywordById(keyword.id.toString())
            .executeAsOneOrNull()?.let { outdatedKeyword ->
                database.keywordsQueries
                    .insertKeyword(
                        id = outdatedKeyword.id,
                        name = outdatedKeyword.name,
                        count = outdatedKeyword.count + 1
                    )
            } ?: database
            .keywordsQueries
            .insertKeyword(
                id = keyword.id.toString(),
                name = keyword.name,
                count = 1
            )
    }
}