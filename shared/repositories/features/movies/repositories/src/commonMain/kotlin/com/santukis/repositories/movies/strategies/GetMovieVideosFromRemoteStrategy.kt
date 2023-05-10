package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Video
import com.santukis.repositories.movies.sources.GetMovieVideosDataSource
import com.santukis.repositories.strategies.RemoteStrategy

class GetMovieVideosFromRemoteStrategy(
    private val getMovieVideosFromRemote: GetMovieVideosDataSource,
): RemoteStrategy<String, List<Video>>() {

    override suspend fun loadFromRemote(input: String): List<Video> =
        getMovieVideosFromRemote.getVideosForMovie(input)

    override suspend fun saveIntoLocal(output: List<Video>): List<Video> =
        output
}