package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesGateway {
    suspend fun getPopularMovies(page: Int): Flow<List<Movie>>
}

class GetPopularMovies(
    private val gateway: GetPopularMoviesGateway
) : UseCase<Int, Flow<List<Movie>>> {

    override suspend fun invoke(params: Int): Flow<List<Movie>> =
        gateway.getPopularMovies(params)
}