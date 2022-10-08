package com.santukis.datasources.remote

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun getHttpClientEngine(): HttpClientEngine = Darwin.create()

actual fun getMovieDataBaseSecret(): String =
    platform.Foundation.NSBundle.mainBundle.infoDictionary?.get("MOVIE_DATABASE_SECRET")
        ?.toString().orEmpty()