package com.santukis.datasources.movies.remote.entities


import com.santukis.entities.movies.Video
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMovieVideosResponseDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("results")
    val results: List<VideoDto>? = null
) {
    fun toVideos(): List<Video> =
        results?.mapNotNull { it.toVideo() }.orEmpty()
}