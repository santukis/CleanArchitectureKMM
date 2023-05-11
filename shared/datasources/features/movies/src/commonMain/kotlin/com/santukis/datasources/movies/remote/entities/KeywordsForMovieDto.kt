package com.santukis.datasources.movies.remote.entities


import com.santukis.entities.movies.Keyword
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeywordsForMovieDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("keywords")
    val keywords: List<KeywordDto>? = null
) {

    fun toKeywords(): List<Keyword> = keywords?.map { it.toKeyword() }.orEmpty()
}