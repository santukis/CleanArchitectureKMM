package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.usecases.movies.outputs.GetMoviesByKeywordOutput
import kotlinx.coroutines.flow.Flow

class GetMoviesByKeyword(
    private val gateway: GetMoviesByKeywordOutput
) : UseCase<Int, Flow<List<Movie>>> {

    override suspend fun invoke(params: Int): Flow<List<Movie>> =
        gateway.getMoviesByKeyword(params)
}