package com.santukis.usecases.configuration

import com.santukis.entities.configuration.Country
import com.santukis.usecases.UseCase
import com.santukis.usecases.configuration.outputs.GetCountriesOutput

class GetCountries(private val gateway: GetCountriesOutput): UseCase<Unit, List<Country>> {

    override suspend fun invoke(params: Unit): List<Country> = gateway.getCountries()
}