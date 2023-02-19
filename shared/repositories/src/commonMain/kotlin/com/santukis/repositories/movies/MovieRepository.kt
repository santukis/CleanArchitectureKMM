package com.santukis.repositories.movies

import com.santukis.entities.movies.Keyword
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.Video
import com.santukis.repositories.strategies.RemoteStrategy
import com.santukis.repositories.strategies.RepositoryStrategy
import com.santukis.usecases.movies.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MovieRepository(
    private val getMovieDetailStrategy: RepositoryStrategy<String, Movie>,
    private val getMovieVideosStrategy: RepositoryStrategy<String, List<Video>>,
    private val getMovieKeywordsStrategy: RepositoryStrategy<String, List<Keyword>>,
    private val getNowPlayingMoviesStrategy: RepositoryStrategy<Unit, List<Movie>>,
    private val getUpcomingMoviesStrategy: RepositoryStrategy<Unit, List<Movie>>,
    private val getPopularMoviesStrategy: RepositoryStrategy<Unit, List<Movie>>,
    private val getMostFrequentlyKeywordsStrategy: RepositoryStrategy<Unit, List<Keyword>>,
    private val getMoviesByKeywordStrategy: RepositoryStrategy<List<Keyword>, List<Movie>>
) :
    GetMovieDetailGateway,
    GetNowPlayingMoviesGateway,
    GetUpcomingMoviesGateway,
    GetPopularMoviesGateway,
    GetMoviesByKeywordGateway,
    GetMovieVideosGateway {

    override suspend fun getMovie(movieId: String): Flow<Movie> =
        flow {
            emit(getMovieDetailStrategy.execute(movieId))
            getMovieKeywordsStrategy.execute(movieId)
        }

    override suspend fun getMovieVideos(movieId: String): Flow<List<Video>> =
        flowOf(getMovieVideosStrategy.execute(movieId))

    override suspend fun getNowPlayingMovies(): Flow<List<Movie>> =
        flowOf(getNowPlayingMoviesStrategy.execute(Unit))

    override suspend fun getUpcomingMovies(): Flow<List<Movie>> =
        flowOf(getUpcomingMoviesStrategy.execute(Unit))

    override suspend fun getPopularMovies(): Flow<List<Movie>> =
        flowOf(getPopularMoviesStrategy.execute(Unit))

    override suspend fun getMoviesByKeyword(): Flow<List<Movie>> =
        flowOf(getMoviesByKeywordStrategy.execute(
                getMostFrequentlyKeywordsStrategy.execute(Unit)))
}