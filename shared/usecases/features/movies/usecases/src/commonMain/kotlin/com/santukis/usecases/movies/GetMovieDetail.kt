package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.usecases.movies.outputs.GetMovieDetailOutput
import kotlinx.coroutines.flow.Flow

class GetMovieDetail(
    private val gateway: GetMovieDetailOutput
) : UseCase<String, Flow<Movie>> {

    override suspend fun invoke(params: String): Flow<Movie> =
        gateway.getMovie(movieId = params)
}