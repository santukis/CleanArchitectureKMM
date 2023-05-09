package com.santukis.usecases.movies

import com.santukis.entities.movies.Video
import com.santukis.usecases.UseCase
import com.santukis.usecases.movies.outputs.GetMovieVideosOutput
import kotlinx.coroutines.flow.Flow

class GetMovieVideos(
    private val gateway: GetMovieVideosOutput
) : UseCase<String, Flow<List<Video>>> {

    override suspend fun invoke(params: String): Flow<List<Video>> =
        gateway.getMovieVideos(movieId = params)
}