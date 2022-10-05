package com.santukis.repositories.sources

import com.santukis.entities.movies.Movie

interface GetMovieDetailDataSource {

    suspend fun getMovie(movieId: String): Movie
}