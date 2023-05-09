package com.santukis.repositories.movies.sources

import com.santukis.entities.movies.Keyword
import com.santukis.entities.movies.Movie

interface GetMoviesByKeywordDataSource {

    suspend fun getMoviesByKeyword(keywords: List<Keyword>, page: Int): List<Movie>
}