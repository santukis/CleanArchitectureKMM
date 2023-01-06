package com.santukis.datasources.movies.remote.services

import com.santukis.datasources.core.remote.KtorClient
import com.santukis.datasources.core.remote.services.MovieDatabaseApi
import com.santukis.datasources.movies.remote.entities.MovieDto
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

class MoviesApi(client: KtorClient): MovieDatabaseApi(client) {

    private val moviePath = "${endPoint}3/movie"

    suspend fun getMovieDetail(movieId: String): MovieDto = client.httpClient.use {
        it.get("$moviePath/$movieId").body()
    }
}