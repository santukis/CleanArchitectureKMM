package com.santukis.datasources.configuration

import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.entities.configuration.Region

actual fun defaultRegion(): Region {
    return Region(
        country = Country(
            name = NSLocale.currentLocale.countryName,
            iso = NSLocale.currentLocale.countryCode
        ),
        language = Language(
            name = NSLocale.currentLocale.languageName,
            iso = NSLocale.currentLocale.languageCode
        )
    )
}