package com.santukis.datasources.configuration

import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.entities.configuration.Region
import java.util.Locale

actual fun defaultRegion(): Region {
    val locale: Locale = Locale.getDefault()

    return Region(
        country = Country(
            name = locale.displayCountry,
            iso = locale.country
        ),
        language = Language(
            name = locale.displayLanguage,
            iso = locale.language
        )
    )
}