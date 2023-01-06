package com.santukis.repositories.movies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetMovieDetailDataSource
import com.santukis.repositories.movies.sources.SaveMovieDetailDataSource
import com.santukis.repositories.strategies.RemoteStrategy
import com.santukis.usecases.movies.GetMovieDetailGateway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val getMovieDetailFromRemote: GetMovieDetailDataSource,
    private val saveMovieDetailToLocal: SaveMovieDetailDataSource
): GetMovieDetailGateway {

    override suspend fun getMovie(movieId: String): Flow<Movie> =
        flow {
            val response = object: RemoteStrategy<String, Movie>() {
                override suspend fun loadFromRemote(input: String): Movie =
                    getMovieDetailFromRemote.getMovie(input)

                override suspend fun saveIntoLocal(output: Movie): Movie =
                    saveMovieDetailToLocal.saveMovie(output)

            }.execute(movieId)

            emit(response)
        }
}