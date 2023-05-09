package com.santukis.usecases.configuration

import com.santukis.entities.configuration.Language
import com.santukis.usecases.UseCase
import com.santukis.usecases.configuration.outputs.GetLanguagesOutput

class GetLanguages(private val gateway: GetLanguagesOutput): UseCase<Unit, List<Language>> {

    override suspend fun invoke(params: Unit): List<Language> = gateway.getLanguages()
}