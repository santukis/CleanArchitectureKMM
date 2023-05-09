package com.santukis.usecases.movies.outputs

import com.santukis.entities.movies.Movie
import kotlinx.coroutines.flow.Flow

interface GetNowPlayingMoviesOutput {
    suspend fun getNowPlayingMovies(): Flow<List<Movie>>
}
