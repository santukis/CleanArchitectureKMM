package com.santukis.repositories.movies

import com.santukis.entities.movies.Keyword
import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.*
import com.santukis.repositories.strategies.RemoteStrategy
import com.santukis.usecases.movies.GetMovieDetailGateway
import com.santukis.usecases.movies.GetNowPlayingMoviesGateway
import com.santukis.usecases.movies.GetPopularMoviesGateway
import com.santukis.usecases.movies.GetUpcomingMoviesGateway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val getMovieDetailFromRemote: GetMovieDetailDataSource,
    private val saveMovieDetailToLocal: SaveMovieDetailDataSource,
    private val getKeywordsForMovieFromRemote: GetKeywordsForMovieDataSource,
    private val saveMovieKeywordsToLocal: SaveMovieKeywordsDataSource,
    private val getNowPlayingMoviesDataSource: GetNowPlayingMoviesDataSource,
    private val getUpcomingMoviesDataSource: GetUpcomingMoviesDataSource,
    private val getPopularMoviesDataSource: GetPopularMoviesDataSource
) :
    GetMovieDetailGateway,
    GetNowPlayingMoviesGateway,
    GetUpcomingMoviesGateway,
    GetPopularMoviesGateway {

    override suspend fun getMovie(movieId: String): Flow<Movie> =
        flow {
            emit(getMovieDetail(movieId))
            getKeywordsForMovie(movieId)
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

    override suspend fun getPopularMovies(): Flow<List<Movie>> =
        flow {
            val response = object : RemoteStrategy<Unit, List<Movie>>() {
                override suspend fun loadFromRemote(input: Unit): List<Movie> =
                    getPopularMoviesDataSource.getPopularMovies()

                override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
                    output

            }.execute(Unit)

            emit(response)
        }

    private suspend fun getMovieDetail(movieId: String): Movie =
        object : RemoteStrategy<String, Movie>() {
            override suspend fun loadFromRemote(input: String): Movie =
                getMovieDetailFromRemote.getMovie(input)

            override suspend fun saveIntoLocal(output: Movie): Movie =
                saveMovieDetailToLocal.saveMovie(output)

        }.execute(movieId)

    private suspend fun getKeywordsForMovie(movieId: String) {
        object : RemoteStrategy<String, List<Keyword>>() {
            override suspend fun loadFromRemote(input: String): List<Keyword> =
                getKeywordsForMovieFromRemote.getKeywordsForMovie(movieId)

            override suspend fun saveIntoLocal(output: List<Keyword>): List<Keyword> {
                saveMovieKeywordsToLocal.saveMovieKeywords(movieId, output)
                return output
            }
        }.execute(movieId)
    }
}