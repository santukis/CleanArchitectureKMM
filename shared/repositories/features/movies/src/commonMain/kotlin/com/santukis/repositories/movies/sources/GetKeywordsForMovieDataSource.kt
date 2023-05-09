package com.santukis.repositories.movies.sources

import com.santukis.entities.movies.Keyword

interface GetKeywordsForMovieDataSource {

    suspend fun getKeywordsForMovie(movieId: String): List<Keyword>
}