package com.santukis.datasources.movies.remote.services

import com.santukis.datasources.core.remote.KtorClient
import com.santukis.datasources.core.remote.services.MovieDatabaseApi
import com.santukis.datasources.movies.remote.entities.GetMoviesRequestDto
import com.santukis.datasources.movies.remote.entities.GetMoviesResponseDto
import com.santukis.datasources.movies.remote.entities.MovieDto
import io.ktor.client.call.*
import io.ktor.client.request.*

class MoviesApi(client: KtorClient): MovieDatabaseApi(client) {

    private val moviePath = "${endPoint}3/movie"

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

    private fun HttpRequestBuilder.addQueryParameters(request: GetMoviesRequestDto): HttpRequestBuilder {
        url {
            parameters.append(language, request.language)
            parameters.append(region, request.region)
            parameters.append(page, request.page)
        }

        return this
    }

}