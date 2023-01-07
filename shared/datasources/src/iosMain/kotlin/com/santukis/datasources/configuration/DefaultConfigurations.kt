package com.santukis.datasources.configuration

import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.entities.configuration.Region
import platform.Foundation.*

actual fun defaultRegion(): Region {
    val currentLocale: NSLocale = NSLocale.currentLocale()
    val countryIso = currentLocale.countryCode.orEmpty()
    val languageIso = currentLocale.languageCode

    return Region(
        country = Country(
            name = currentLocale.localizedStringForCountryCode(countryIso).orEmpty(),
            iso = countryIso
        ),
        language = Language(
            name = currentLocale.localizedStringForLanguageCode(languageIso).orEmpty(),
            iso = languageIso
        )
    )
}