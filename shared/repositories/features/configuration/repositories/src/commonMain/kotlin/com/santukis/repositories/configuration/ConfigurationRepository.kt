package com.santukis.repositories.configuration

import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.repositories.configuration.sources.GetCountriesDataSource
import com.santukis.repositories.configuration.sources.GetLanguagesDataSource
import com.santukis.repositories.strategies.RemoteStrategy
import com.santukis.usecases.configuration.outputs.GetCountriesOutput
import com.santukis.usecases.configuration.outputs.GetLanguagesOutput

class ConfigurationRepository(
    private val getCountriesDataSource: GetCountriesDataSource,
    private val getLanguagesDataSource: GetLanguagesDataSource
):
    GetCountriesOutput,
    GetLanguagesOutput {

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