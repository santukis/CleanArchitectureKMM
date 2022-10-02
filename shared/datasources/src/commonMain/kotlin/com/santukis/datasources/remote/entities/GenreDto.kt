package com.santukis.datasources.remote.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null
)