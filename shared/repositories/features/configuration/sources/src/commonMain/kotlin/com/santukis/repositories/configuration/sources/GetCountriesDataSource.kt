package com.santukis.repositories.configuration.sources

import com.santukis.entities.configuration.Country

interface GetCountriesDataSource {

    suspend fun getCountries(): List<Country>
}