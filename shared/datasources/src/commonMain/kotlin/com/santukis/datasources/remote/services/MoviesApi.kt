package com.santukis.datasources.remote.services

import com.santukis.datasources.remote.KtorClient
import com.santukis.datasources.remote.entities.MovieDto
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

class MoviesApi(private val client: KtorClient) {

    companion object {
        private const val END_POINT = "https://api.themoviedb.org/"
    }

    suspend fun getMovieDetail(movieId: String): MovieDto = client.httpClient.use {
        it.get("${END_POINT}3/movie/$movieId").body()
    }
}