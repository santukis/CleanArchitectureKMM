package com.santukis.usecases.movies.outputs

import com.santukis.entities.movies.Video
import kotlinx.coroutines.flow.Flow

interface GetMovieVideosOutput {
    suspend fun getMovieVideos(movieId: String): Flow<List<Video>>
}