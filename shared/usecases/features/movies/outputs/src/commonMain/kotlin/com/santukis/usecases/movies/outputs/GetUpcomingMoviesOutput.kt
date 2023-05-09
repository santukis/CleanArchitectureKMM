package com.santukis.usecases.movies.outputs

import com.santukis.entities.movies.Movie
import kotlinx.coroutines.flow.Flow

interface GetUpcomingMoviesOutput {
    suspend fun getUpcomingMovies(page: Int): Flow<List<Movie>>
}