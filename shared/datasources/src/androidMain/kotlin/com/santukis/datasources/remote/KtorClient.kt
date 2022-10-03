package com.santukis.datasources.remote

import com.santukis.datasources.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import java.util.concurrent.TimeUnit

actual fun buildClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)

    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(10, TimeUnit.SECONDS)
        }
    }
}

actual fun getMovieDataBaseSecret(): String = BuildConfig.MOVIE_DATABASE_SECRET