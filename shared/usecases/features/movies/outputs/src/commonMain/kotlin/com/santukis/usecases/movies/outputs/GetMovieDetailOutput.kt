package com.santukis.usecases.movies.outputs

import com.santukis.entities.movies.Movie
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailOutput {
    suspend fun getMovie(movieId: String): Flow<Movie>
}