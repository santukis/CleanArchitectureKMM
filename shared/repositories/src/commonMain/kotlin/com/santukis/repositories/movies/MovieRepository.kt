package com.santukis.repositories.movies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.sources.GetMovieDetailDataSource
import com.santukis.repositories.sources.SaveMovieDetailDataSource
import com.santukis.repositories.strategies.RemoteStrategy
import com.santukis.usecases.movies.GetMovieDetailGateway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val getMovieDetailSourceFromRemote: GetMovieDetailDataSource,
    private val saveMovieDetailSourceToLocal: SaveMovieDetailDataSource
): GetMovieDetailGateway {

    override suspend fun getMovie(movieId: String): Flow<Movie> =
        flow {
            val response = object: RemoteStrategy<String, Movie>() {
                override suspend fun loadFromRemote(input: String): Movie =
                    getMovieDetailSourceFromRemote.getMovie(input)

                override suspend fun saveIntoLocal(output: Movie): Movie =
                    saveMovieDetailSourceToLocal.saveMovie(output)

            }.execute(movieId)

            emit(response)
        }
}