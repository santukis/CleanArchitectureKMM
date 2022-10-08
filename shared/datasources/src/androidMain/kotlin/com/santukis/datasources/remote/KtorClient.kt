package com.santukis.datasources.remote

import com.santukis.datasources.BuildConfig
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual fun getHttpClientEngine(): HttpClientEngine = OkHttp.create {

}

actual fun getMovieDataBaseSecret(): String = BuildConfig.MOVIE_DATABASE_SECRET