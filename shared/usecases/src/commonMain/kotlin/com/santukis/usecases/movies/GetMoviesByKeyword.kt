package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetMoviesByKeywordGateway {
    suspend fun getMoviesByKeyword(): Flow<List<Movie>>
}

class GetMoviesByKeyword(
    private val gateway: GetMoviesByKeywordGateway
) : UseCase<Unit, Flow<List<Movie>>> {

    override suspend fun invoke(params: Unit): Flow<List<Movie>> =
        gateway.getMoviesByKeyword()
}