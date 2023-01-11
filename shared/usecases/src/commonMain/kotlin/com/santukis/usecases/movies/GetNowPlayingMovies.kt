package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetNowPlayingMoviesGateway {
    suspend fun getNowPlayingMovies(): Flow<List<Movie>>
}

class GetNowPlayingMovies(
    private val gateway: GetNowPlayingMoviesGateway
) : UseCase<Unit, Flow<List<Movie>>> {

    override suspend fun invoke(params: Unit): Flow<List<Movie>> =
        gateway.getNowPlayingMovies()
}