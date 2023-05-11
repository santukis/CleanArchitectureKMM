package com.santukis.datasources.configuration.remote.entities


import com.santukis.entities.configuration.Language
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguageDto(
    @SerialName("iso_639_1")
    val iso6391: String? = null,
    @SerialName("english_name")
    val englishName: String? = null,
    @SerialName("name")
    val name: String? = null
) {
    fun toLanguage(): Language =
        Language(
            name = englishName ?: name.orEmpty(),
            iso = iso6391.orEmpty()
        )
}