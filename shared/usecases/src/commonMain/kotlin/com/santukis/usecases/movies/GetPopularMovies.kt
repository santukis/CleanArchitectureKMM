package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesGateway {
    suspend fun getPopularMovies(): Flow<List<Movie>>
}

class GetPopularMovies(
    private val gateway: GetPopularMoviesGateway
) : UseCase<Unit, Flow<List<Movie>>> {

    override suspend fun invoke(params: Unit): Flow<List<Movie>> =
        gateway.getPopularMovies()
}