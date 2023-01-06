package com.santukis.datasources.configuration.remote.entities


import com.santukis.entities.configuration.Country
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    @SerialName("iso_3166_1")
    val iso31661: String? = null,
    @SerialName("english_name")
    val englishName: String? = null,
    @SerialName("native_name")
    val nativeName: String? = null
) {
    fun toCountry(): Country =
        Country(
            name = englishName ?: nativeName.orEmpty(),
            iso = iso31661.orEmpty()
        )
}