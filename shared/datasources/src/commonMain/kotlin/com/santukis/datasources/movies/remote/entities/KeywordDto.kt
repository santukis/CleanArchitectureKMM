package com.santukis.datasources.movies.remote.entities


import com.santukis.entities.movies.Keyword
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeywordDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null
) {

    fun toKeyword(): Keyword =
        Keyword(
            id = id ?: 0,
            name = name.orEmpty()
        )
}