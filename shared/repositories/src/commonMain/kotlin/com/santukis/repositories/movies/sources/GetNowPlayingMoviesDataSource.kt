package com.santukis.repositories.movies.sources

import com.santukis.entities.movies.Movie

interface GetNowPlayingMoviesDataSource {

    suspend fun getNowPlayingMovies(): List<Movie>
}