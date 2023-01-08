package com.santukis.entities.movies

import com.santukis.entities.configuration.Language

data class Titles(
    val title: String,
    val original: String,
    val originalLanguage: Language
)
