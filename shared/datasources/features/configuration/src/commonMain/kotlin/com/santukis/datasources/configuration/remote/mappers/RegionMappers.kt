package com.santukis.datasources.configuration.remote.mappers

import com.santukis.datasources.configuration.remote.entities.CountryDto
import com.santukis.datasources.configuration.remote.entities.LanguageDto
import com.santukis.datasources.configuration.remote.entities.RegionDto
import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.entities.configuration.Region

fun Region.toRegionDto(): RegionDto =
    RegionDto(
        country = country.toCountryDto(),
        language = language.toLanguageDto()
    )

fun Country.toCountryDto(): CountryDto =
    CountryDto(
        iso31661 = iso,
        englishName = name
    )

fun Language.toLanguageDto(): LanguageDto =
    LanguageDto(
        iso6391 = iso,
        englishName = name
    )