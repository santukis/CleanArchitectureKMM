package com.santukis.repositories.configuration.sources

import com.santukis.entities.configuration.Language

interface GetLanguagesDataSource {

    suspend fun getLanguages(): List<Language>
}