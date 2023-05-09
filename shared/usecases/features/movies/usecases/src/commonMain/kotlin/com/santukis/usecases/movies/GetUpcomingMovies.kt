package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.usecases.movies.outputs.GetUpcomingMoviesOutput
import kotlinx.coroutines.flow.Flow

class GetUpcomingMovies(
    private val gateway: GetUpcomingMoviesOutput
) : UseCase<Int, Flow<List<Movie>>> {

    override suspend fun invoke(params: Int): Flow<List<Movie>> =
        gateway.getUpcomingMovies(params)
}