package com.santukis.entities.configuration

data class Region(
    val country: Country,
    val language: Language
) {

    fun getIso(): String = "${language.iso}-${country.iso}"
}