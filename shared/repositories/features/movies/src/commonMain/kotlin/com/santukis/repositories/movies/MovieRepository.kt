package com.santukis.repositories.movies

import com.santukis.entities.movies.Keyword
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.Video
import com.santukis.repositories.movies.entities.GetMoviesByKeywordRequest
import com.santukis.repositories.strategies.RepositoryStrategy
import com.santukis.usecases.movies.outputs.GetMovieDetailOutput
import com.santukis.usecases.movies.outputs.GetMovieVideosOutput
import com.santukis.usecases.movies.outputs.GetMoviesByKeywordOutput
import com.santukis.usecases.movies.outputs.GetNowPlayingMoviesOutput
import com.santukis.usecases.movies.outputs.GetPopularMoviesOutput
import com.santukis.usecases.movies.outputs.GetUpcomingMoviesOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MovieRepository(
    private val getMovieDetailStrategy: RepositoryStrategy<String, Movie>,
    private val getMovieVideosStrategy: RepositoryStrategy<String, List<Video>>,
    private val getMovieKeywordsStrategy: RepositoryStrategy<String, List<Keyword>>,
    private val getNowPlayingMoviesStrategy: RepositoryStrategy<Unit, List<Movie>>,
    private val getUpcomingMoviesStrategy: RepositoryStrategy<Int, List<Movie>>,
    private val getPopularMoviesStrategy: RepositoryStrategy<Int, List<Movie>>,
    private val getMostFrequentlyKeywordsStrategy: RepositoryStrategy<Unit, List<Keyword>>,
    private val getMoviesByKeywordStrategy: RepositoryStrategy<GetMoviesByKeywordRequest, List<Movie>>
) :
    GetMovieDetailOutput,
    GetNowPlayingMoviesOutput,
    GetUpcomingMoviesOutput,
    GetPopularMoviesOutput,
    GetMoviesByKeywordOutput,
    GetMovieVideosOutput {

    override suspend fun getMovie(movieId: String): Flow<Movie> =
        flow {
            emit(getMovieDetailStrategy.execute(movieId))
            getMovieKeywordsStrategy.execute(movieId)
        }

    override suspend fun getMovieVideos(movieId: String): Flow<List<Video>> =
        flowOf(getMovieVideosStrategy.execute(movieId))

    override suspend fun getNowPlayingMovies(): Flow<List<Movie>> =
        flowOf(getNowPlayingMoviesStrategy.execute(Unit))

    override suspend fun getUpcomingMovies(page: Int): Flow<List<Movie>> =
        flowOf(getUpcomingMoviesStrategy.execute(page))

    override suspend fun getPopularMovies(page: Int): Flow<List<Movie>> =
        flowOf(getPopularMoviesStrategy.execute(page))

    override suspend fun getMoviesByKeyword(page: Int): Flow<List<Movie>> =
        flowOf(getMoviesByKeywordStrategy.execute(
            GetMoviesByKeywordRequest(
                keywords = getMostFrequentlyKeywordsStrategy.execute(Unit),
                page = page
            ))
        )
}