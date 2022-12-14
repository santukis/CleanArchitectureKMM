package com.santukis.datasources.core.remote.services

import com.santukis.datasources.core.remote.KtorClient

abstract class MovieDatabaseApi(protected val client: KtorClient) {
    protected val endPoint = "https://api.themoviedb.org/"

    protected val language = "language"
    protected val region = "region"
    protected val page = "page"
}