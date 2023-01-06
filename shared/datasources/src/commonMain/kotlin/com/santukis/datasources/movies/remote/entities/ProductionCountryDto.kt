package com.santukis.datasources.movies.remote.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryDto(
    @SerialName("iso_3166_1")
    val iso: String? = null,
    @SerialName("name")
    val name: String? = null
)