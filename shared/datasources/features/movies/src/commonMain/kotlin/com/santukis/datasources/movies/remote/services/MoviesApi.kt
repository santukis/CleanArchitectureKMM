package com.santukis.datasources.movies.remote.services

import com.santukis.datasources.remote.KtorClient
import com.santukis.datasources.remote.services.MovieDatabaseApi
import com.santukis.datasources.movies.remote.entities.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MoviesApi(client: KtorClient): MovieDatabaseApi(client) {

    private val moviePath = "${endPoint}3/movie"
    private val keywordPath = "${endPoint}3/keyword"

    suspend fun getMovieDetail(movieId: String): MovieDto =
        client.httpClient.get("$moviePath/$movieId").body()

    suspend fun getNowPlaying(request: GetMoviesRequestDto): GetMoviesResponseDto =
        client.httpClient.get("$moviePath/now_playing") {
            addQueryParameters(request)
        }.body()

    suspend fun getUpcoming(request: GetMoviesRequestDto): GetMoviesResponseDto =
        client.httpClient.get("$moviePath/upcoming") {
            addQueryParameters(request)
        }.body()

    suspend fun getPopular(request: GetMoviesRequestDto): GetMoviesResponseDto =
        client.httpClient.get("$moviePath/popular") {
            addQueryParameters(request)
        }.body()

    suspend fun getKeywordsForMovie(movieId: String): KeywordsForMovieDto =
        client.httpClient.get("$moviePath/$movieId/keywords").body()

    suspend fun getMoviesForKeyword(keywordId: Int, request: GetMoviesRequestDto): GetMoviesResponseDto =
        client.httpClient.get("$keywordPath/$keywordId/movies"){
            addQueryParameters(request)
        }.body()

    suspend fun getMovieVideos(movieId: String, request: GetMoviesRequestDto): GetMovieVideosResponseDto =
        client.httpClient.get("$moviePath/$movieId/videos"){
            addQueryParameters(request)
        }.body()

    private fun HttpRequestBuilder.addQueryParameters(request: GetMoviesRequestDto): HttpRequestBuilder {
        url {
            parameters.append(language, request.language)
            parameters.append(region, request.region)
            parameters.append(page, request.page)
        }

        return this
    }

}