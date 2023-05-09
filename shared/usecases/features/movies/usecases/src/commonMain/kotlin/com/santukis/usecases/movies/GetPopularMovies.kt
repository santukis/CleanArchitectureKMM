package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.usecases.movies.outputs.GetPopularMoviesOutput
import kotlinx.coroutines.flow.Flow

class GetPopularMovies(
    private val gateway: GetPopularMoviesOutput
) : UseCase<Int, Flow<List<Movie>>> {

    override suspend fun invoke(params: Int): Flow<List<Movie>> =
        gateway.getPopularMovies(params)
}