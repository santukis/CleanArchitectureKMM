package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetUpcomingMoviesGateway {
    suspend fun getUpcomingMovies(page: Int): Flow<List<Movie>>
}

class GetUpcomingMovies(
    private val gateway: GetUpcomingMoviesGateway
) : UseCase<Int, Flow<List<Movie>>> {

    override suspend fun invoke(params: Int): Flow<List<Movie>> =
        gateway.getUpcomingMovies(params)
}