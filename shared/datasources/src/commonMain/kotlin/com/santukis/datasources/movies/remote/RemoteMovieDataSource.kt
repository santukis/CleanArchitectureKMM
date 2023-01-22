package com.santukis.datasources.movies.remote

import com.santukis.datasources.movies.remote.entities.GetMoviesRequestDto
import com.santukis.datasources.movies.remote.services.MoviesApi
import com.santukis.entities.movies.Keyword
import com.santukis.entities.movies.Movie
import com.santukis.repositories.configuration.sources.GetRegionDataSource
import com.santukis.repositories.movies.sources.*

class RemoteMovieDataSource(
    private val moviesApi: MoviesApi,
    private val getRegionDataSource: GetRegionDataSource
) :
    GetMovieDetailDataSource,
    GetNowPlayingMoviesDataSource,
    GetUpcomingMoviesDataSource,
    GetPopularMoviesDataSource,
    GetKeywordsForMovieDataSource,
    GetMoviesByKeywordDataSource {

    override suspend fun getMovie(movieId: String): Movie {
        return moviesApi.getMovieDetail(movieId).toMovie()
    }

    override suspend fun getNowPlayingMovies(): List<Movie> {
        return moviesApi.getNowPlaying(buildMoviesRequestDto()).toMovies()
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        return moviesApi.getUpcoming(buildMoviesRequestDto()).toMovies()
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return moviesApi.getPopular(buildMoviesRequestDto()).toMovies()
    }

    override suspend fun getKeywordsForMovie(movieId: String): List<Keyword> {
        return moviesApi.getKeywordsForMovie(movieId).toKeywords()
    }

    override suspend fun getMoviesByKeyword(keywords: List<Keyword>): List<Movie> {
        val request = buildMoviesRequestDto()
        return keywords
            .flatMap { keyword -> moviesApi.getMoviesForKeyword(keyword.id, request).toMovies() }
            .distinctBy { movie ->  movie.id }
    }

    private suspend fun buildMoviesRequestDto(): GetMoviesRequestDto {
        val region = getRegionDataSource.getRegion()
        return GetMoviesRequestDto(
            language = region.getIso(),
            region = region.country.iso,
            page = 1.toString()
        )
    }
}