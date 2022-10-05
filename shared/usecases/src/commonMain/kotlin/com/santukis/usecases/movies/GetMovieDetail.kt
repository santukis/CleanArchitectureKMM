package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailGateway {
    suspend fun getMovie(movieId: String): Flow<Movie>
}

class GetMovieDetail(
    private val gateway: GetMovieDetailGateway
) : UseCase<String, Flow<Movie>> {

    override suspend fun invoke(params: String): Flow<Movie> =
        gateway.getMovie(movieId = params)
}