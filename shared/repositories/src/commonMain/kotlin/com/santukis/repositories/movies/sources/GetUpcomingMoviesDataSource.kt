package com.santukis.repositories.movies.sources

import com.santukis.entities.movies.Movie

interface GetUpcomingMoviesDataSource {

    suspend fun getUpcomingMovies(): List<Movie>
}