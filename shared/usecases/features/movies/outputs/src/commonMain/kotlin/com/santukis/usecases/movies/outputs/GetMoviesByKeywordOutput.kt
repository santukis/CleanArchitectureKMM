package com.santukis.usecases.movies.outputs

import com.santukis.entities.movies.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesByKeywordOutput {
    suspend fun getMoviesByKeyword(page: Int): Flow<List<Movie>>
}