package com.santukis.datasources.movies.remote.entities


import com.santukis.entities.movies.BackdropImage
import com.santukis.entities.movies.Images
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.PosterImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int?>? = null,
    @SerialName("original_language")
    val originalLanguage: String? = null,
    @SerialName("original_title")
    val originalTitle: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("video")
    val video: Boolean? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("vote_count")
    val voteCount: Int? = null
) {

    fun toMovie(): Movie =
        Movie(
            id = id ?: 0,
            imdbId = "",
            images = Images(
                backdropImage = BackdropImage(path = backdropPath.orEmpty()),
                posterImage = PosterImage(path = posterPath.orEmpty())
            )
        )
}