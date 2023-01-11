package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetUpcomingMoviesGateway {
    suspend fun getUpcomingMovies(): Flow<List<Movie>>
}

class GetUpcomingMovies(
    private val gateway: GetUpcomingMoviesGateway
) : UseCase<Unit, Flow<List<Movie>>> {

    override suspend fun invoke(params: Unit): Flow<List<Movie>> =
        gateway.getUpcomingMovies()
}