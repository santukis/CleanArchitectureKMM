package com.santukis.datasources.movies.remote

import com.santukis.datasources.movies.remote.entities.GetMoviesRequestDto
import com.santukis.datasources.movies.remote.services.MoviesApi
import com.santukis.entities.movies.Movie
import com.santukis.repositories.configuration.sources.GetRegionDataSource
import com.santukis.repositories.movies.sources.GetMovieDetailDataSource
import com.santukis.repositories.movies.sources.GetNowPlayingMoviesDataSource

class RemoteMovieDataSource(
    private val moviesApi: MoviesApi,
    private val getRegionDataSource: GetRegionDataSource
) :
    GetMovieDetailDataSource,
    GetNowPlayingMoviesDataSource {

    override suspend fun getMovie(movieId: String): Movie {
        return moviesApi.getMovieDetail(movieId).toMovie()
    }

    override suspend fun getNowPlayingMovies(): List<Movie> {
        val region = getRegionDataSource.getRegion()
        val requestDto = GetMoviesRequestDto(
            language = region.getIso(),
            region = region.country.iso,
            page = 1.toString()
        )

        return moviesApi.getNowPlaying(requestDto).toMovies()
    }
}