package com.santukis.datasources.remote.services

import com.santukis.datasources.remote.KtorClient

abstract class MovieDatabaseApi(protected val client: KtorClient) {
    protected val endPoint = "https://api.themoviedb.org/"

    protected val language = "language"
    protected val region = "region"
    protected val page = "page"
}