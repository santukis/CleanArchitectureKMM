package com.santukis.usecases.configuration

import com.santukis.entities.configuration.Language
import com.santukis.usecases.UseCase

interface GetLanguagesGateway {
    suspend fun getLanguages(): List<Language>
}

class GetLanguages(private val gateway: GetLanguagesGateway): UseCase<Unit, List<Language>> {

    override suspend fun invoke(params: Unit): List<Language> = gateway.getLanguages()
}