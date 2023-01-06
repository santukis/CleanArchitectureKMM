package com.santukis.datasources.configuration.remote

import com.santukis.datasources.configuration.remote.services.ConfigurationApi
import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.repositories.configuration.sources.GetCountriesDataSource
import com.santukis.repositories.configuration.sources.GetLanguagesDataSource

class RemoteConfigurationDataSource(
    private val configurationApi: ConfigurationApi
):
    GetCountriesDataSource,
    GetLanguagesDataSource {

    override suspend fun getCountries(): List<Country> =
        configurationApi.getCountries().map { it.toCountry() }

    override suspend fun getLanguages(): List<Language> =
        configurationApi.getLanguages().map { it.toLanguage() }
}