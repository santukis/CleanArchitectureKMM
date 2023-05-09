package com.santukis.usecases.configuration.outputs

import com.santukis.entities.configuration.Language

interface GetLanguagesOutput {
    suspend fun getLanguages(): List<Language>
}