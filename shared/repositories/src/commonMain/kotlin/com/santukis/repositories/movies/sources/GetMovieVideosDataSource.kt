package com.santukis.repositories.movies.sources

import com.santukis.entities.movies.Video

interface GetMovieVideosDataSource {

    suspend fun getVideosForMovie(movieId: String): List<Video>
}