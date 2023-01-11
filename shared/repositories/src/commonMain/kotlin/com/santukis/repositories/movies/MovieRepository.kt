package com.santukis.repositories.movies

import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.GetMovieDetailDataSource
import com.santukis.repositories.movies.sources.GetNowPlayingMoviesDataSource
import com.santukis.repositories.movies.sources.GetUpcomingMoviesDataSource
import com.santukis.repositories.movies.sources.SaveMovieDetailDataSource
import com.santukis.repositories.strategies.RemoteStrategy
import com.santukis.usecases.movies.GetMovieDetailGateway
import com.santukis.usecases.movies.GetNowPlayingMoviesGateway
import com.santukis.usecases.movies.GetUpcomingMoviesGateway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val getMovieDetailFromRemote: GetMovieDetailDataSource,
    private val saveMovieDetailToLocal: SaveMovieDetailDataSource,
    private val getNowPlayingMoviesDataSource: GetNowPlayingMoviesDataSource,
    private val getUpcomingMoviesDataSource: GetUpcomingMoviesDataSource
) :
    GetMovieDetailGateway,
    GetNowPlayingMoviesGateway,
    GetUpcomingMoviesGateway {

    override suspend fun getMovie(movieId: String): Flow<Movie> =
        flow {
            val response = object : RemoteStrategy<String, Movie>() {
                override suspend fun loadFromRemote(input: String): Movie =
                    getMovieDetailFromRemote.getMovie(input)

                override suspend fun saveIntoLocal(output: Movie): Movie =
                    saveMovieDetailToLocal.saveMovie(output)

            }.execute(movieId)

            emit(response)
        }

    override suspend fun getNowPlayingMovies(): Flow<List<Movie>> =
        flow {
            val response = object : RemoteStrategy<Unit, List<Movie>>() {
                override suspend fun loadFromRemote(input: Unit): List<Movie> =
                    getNowPlayingMoviesDataSource.getNowPlayingMovies()

                override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
                    output

            }.execute(Unit)

            emit(response)
        }

    override suspend fun getUpcomingMovies(): Flow<List<Movie>> =
        flow {
            val response = object : RemoteStrategy<Unit, List<Movie>>() {
                override suspend fun loadFromRemote(input: Unit): List<Movie> =
                    getUpcomingMoviesDataSource.getUpcomingMovies()

                override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
                    output

            }.execute(Unit)

            emit(response)
        }
}