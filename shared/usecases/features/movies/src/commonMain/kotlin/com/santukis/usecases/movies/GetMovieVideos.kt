package com.santukis.usecases.movies

import com.santukis.entities.movies.Video
import com.santukis.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface GetMovieVideosGateway {
    suspend fun getMovieVideos(movieId: String): Flow<List<Video>>
}

class GetMovieVideos(
    private val gateway: GetMovieVideosGateway
) : UseCase<String, Flow<List<Video>>> {

    override suspend fun invoke(params: String): Flow<List<Video>> =
        gateway.getMovieVideos(movieId = params)
}