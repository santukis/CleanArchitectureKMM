package com.santukis.datasources.movies.remote.entities


import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.entities.configuration.Region
import com.santukis.entities.movies.Video
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("iso_639_1")
    val iso6391: String? = null,
    @SerialName("iso_3166_1")
    val iso31661: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("key")
    val key: String? = null,
    @SerialName("site")
    val site: String? = null,
    @SerialName("size")
    val size: Int? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("official")
    val official: Boolean? = null,
    @SerialName("published_at")
    val publishedAt: String? = null
) {

    fun toVideo(): Video? =
        takeIf { site?.lowercase() == "youtube" }?.let {
            Video(
                id = id.orEmpty(),
                region = Region(
                    country = Country(iso = iso31661.orEmpty()),
                    language = Language(iso = iso6391.orEmpty())
                ),
                name = name.orEmpty(),
                type = type.orEmpty(),
                size = size ?: 0,
                official = official ?: false,
                url = key.orEmpty()
            )
        }
}