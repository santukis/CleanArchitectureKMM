package com.santukis.usecases.configuration

import com.santukis.entities.configuration.Country
import com.santukis.usecases.UseCase

interface GetCountriesGateway {
    suspend fun getCountries(): List<Country>
}

class GetCountries(private val gateway: GetCountriesGateway): UseCase<Unit, List<Country>> {

    override suspend fun invoke(params: Unit): List<Country> = gateway.getCountries()
}