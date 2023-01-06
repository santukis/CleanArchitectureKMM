package com.santukis.datasources.movies.remote.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DatesDto(
    @SerialName("maximum")
    val maximum: String? = null,
    @SerialName("minimum")
    val minimum: String? = null
)