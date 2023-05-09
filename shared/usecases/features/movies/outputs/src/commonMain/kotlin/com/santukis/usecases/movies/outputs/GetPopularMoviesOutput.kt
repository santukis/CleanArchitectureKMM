package com.santukis.usecases.movies.outputs

import com.santukis.entities.movies.Movie
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesOutput {
    suspend fun getPopularMovies(page: Int): Flow<List<Movie>>
}