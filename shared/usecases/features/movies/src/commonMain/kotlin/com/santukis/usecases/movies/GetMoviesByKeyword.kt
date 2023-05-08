package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetMoviesByKeywordGateway {
    suspend fun getMoviesByKeyword(page: Int): Flow<List<Movie>>
}

class GetMoviesByKeyword(
    private val gateway: GetMoviesByKeywordGateway
) : UseCase<Int, Flow<List<Movie>>> {

    override suspend fun invoke(params: Int): Flow<List<Movie>> =
        gateway.getMoviesByKeyword(params)
}