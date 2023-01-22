package com.santukis.repositories.movies

import com.santukis.entities.movies.Keyword
import com.santukis.entities.movies.Movie
import com.santukis.repositories.movies.sources.*
import com.santukis.repositories.strategies.RemoteStrategy
import com.santukis.usecases.movies.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val getMovieDetailFromRemote: GetMovieDetailDataSource,
    private val saveMovieDetailToLocal: SaveMovieDetailDataSource,
    private val getKeywordsForMovieFromRemote: GetKeywordsForMovieDataSource,
    private val saveMovieKeywordsToLocal: SaveMovieKeywordsDataSource,
    private val getNowPlayingMoviesFromRemote: GetNowPlayingMoviesDataSource,
    private val getUpcomingMoviesFromRemote: GetUpcomingMoviesDataSource,
    private val getPopularMoviesFromRemote: GetPopularMoviesDataSource,
    private val getMostFrequentlyKeywordsFromLocal: GetMostFrequentlyKeywordsDataSource,
    private val getMoviesByKeywordFromRemote: GetMoviesByKeywordDataSource
) :
    GetMovieDetailGateway,
    GetNowPlayingMoviesGateway,
    GetUpcomingMoviesGateway,
    GetPopularMoviesGateway,
    GetMoviesByKeywordGateway {

    override suspend fun getMovie(movieId: String): Flow<Movie> =
        flow {
            emit(getMovieDetail(movieId))
            getKeywordsForMovie(movieId)
        }

    override suspend fun getNowPlayingMovies(): Flow<List<Movie>> =
        flow {
            val response = object : RemoteStrategy<Unit, List<Movie>>() {
                override suspend fun loadFromRemote(input: Unit): List<Movie> =
                    getNowPlayingMoviesFromRemote.getNowPlayingMovies()

                override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
                    output

            }.execute(Unit)

            emit(response)
        }

    override suspend fun getUpcomingMovies(): Flow<List<Movie>> =
        flow {
            val response = object : RemoteStrategy<Unit, List<Movie>>() {
                override suspend fun loadFromRemote(input: Unit): List<Movie> =
                    getUpcomingMoviesFromRemote.getUpcomingMovies()

                override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
                    output

            }.execute(Unit)

            emit(response)
        }

    override suspend fun getPopularMovies(): Flow<List<Movie>> =
        flow {
            val response = object : RemoteStrategy<Unit, List<Movie>>() {
                override suspend fun loadFromRemote(input: Unit): List<Movie> =
                    getPopularMoviesFromRemote.getPopularMovies()

                override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
                    output

            }.execute(Unit)

            emit(response)
        }

    override suspend fun getMoviesByKeyword(): Flow<List<Movie>> =
        flow {
            val response = object : RemoteStrategy<List<Keyword>, List<Movie>>() {
                override suspend fun loadFromRemote(input: List<Keyword>): List<Movie> =
                    getMoviesByKeywordFromRemote.getMoviesByKeyword(input)

                override suspend fun saveIntoLocal(output: List<Movie>): List<Movie> =
                    output

            }.execute(getMostFrequentlyKeywordsFromLocal.getMostFrequentlyKeywords())

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