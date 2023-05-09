package com.santukis.usecases.configuration.outputs

import com.santukis.entities.configuration.Country

interface GetCountriesOutput {
    suspend fun getCountries(): List<Country>
}