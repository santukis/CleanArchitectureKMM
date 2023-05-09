package com.santukis.usecases.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.usecases.movies.outputs.GetNowPlayingMoviesOutput
import kotlinx.coroutines.flow.Flow

class GetNowPlayingMovies(
    private val gateway: GetNowPlayingMoviesOutput
) : UseCase<Unit, Flow<List<Movie>>> {

    override suspend fun invoke(params: Unit): Flow<List<Movie>> =
        gateway.getNowPlayingMovies()
}