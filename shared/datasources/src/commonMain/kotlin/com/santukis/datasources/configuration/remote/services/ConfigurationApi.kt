package com.santukis.datasources.configuration.remote.services

import com.santukis.datasources.configuration.remote.entities.CountryDto
import com.santukis.datasources.configuration.remote.entities.LanguageDto
import com.santukis.datasources.core.remote.KtorClient
import com.santukis.datasources.core.remote.services.MovieDatabaseApi
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

class ConfigurationApi(client: KtorClient): MovieDatabaseApi(client) {

    private val configurationPath = "${endPoint}3/configuration"

    suspend fun getCountries(): List<CountryDto> =
        client.httpClient.get("$configurationPath/countries").body()

    suspend fun getLanguages(): List<LanguageDto> =
        client.httpClient.get("$configurationPath/languages").body()
}