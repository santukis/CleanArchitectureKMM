package com.santukis.repositories.movies.sources

import com.santukis.entities.movies.Keyword

interface SaveMovieKeywordsDataSource {

    suspend fun saveMovieKeywords(movieId: String, keywords: List<Keyword>)
}