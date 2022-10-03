package com.santukis.datasources.remote

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual fun buildClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
    config(this)
    engine {
        configureRequest {

        }
    }
}

actual fun getMovieDataBaseSecret(): String =
    platform.Foundation.NSBundle.mainBundle.infoDictionary?.get("MOVIE_DATABASE_SECRET")
        ?.toString().orEmpty()