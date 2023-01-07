package com.santukis.datasources.configuration.remote.entities

import com.santukis.entities.configuration.Region
import kotlinx.serialization.Serializable

@Serializable
data class RegionDto(
    val country: CountryDto,
    val language: LanguageDto
) {

    fun toRegion(): Region =
        Region(
            country = country.toCountry(),
            language = language.toLanguage()
        )
}