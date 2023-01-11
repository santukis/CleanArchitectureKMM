package com.santukis.repositories.movies.sources

import com.santukis.entities.movies.Movie

interface GetPopularMoviesDataSource {

    suspend fun getPopularMovies(): List<Movie>
}