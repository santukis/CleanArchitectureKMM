package com.santukis.repositories.configuration

import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.repositories.configuration.sources.GetCountriesDataSource
import com.santukis.repositories.configuration.sources.GetLanguagesDataSource
import com.santukis.repositories.strategies.RemoteStrategy
import com.santukis.usecases.configuration.GetCountriesGateway
import com.santukis.usecases.configuration.GetLanguagesGateway

class ConfigurationRepository(
    private val getCountriesDataSource: GetCountriesDataSource,
    private val getLanguagesDataSource: GetLanguagesDataSource
):
    GetCountriesGateway,
    GetLanguagesGateway {

    override suspend fun getCountries(): List<Country> {
        return object : RemoteStrategy<Unit, List<Country>>() {
            override suspend fun loadFromRemote(input: Unit): List<Country> =
                getCountriesDataSource.getCountries()

            override suspend fun saveIntoLocal(output: List<Country>): List<Country> =
                output

        }.execute(Unit)
    }

    override suspend fun getLanguages(): List<Language> {
        return object : RemoteStrategy<Unit, List<Language>>() {
            override suspend fun loadFromRemote(input: Unit): List<Language> =
                getLanguagesDataSource.getLanguages()

            override suspend fun saveIntoLocal(output: List<Language>): List<Language> =
                output

        }.execute(Unit)
    }
}