package com.santukis.datasources.movies.remote.entities

import com.santukis.entities.movies.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("imdb_id")
    val imdbId: String? = null,
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("budget")
    val budget: Int? = null,
    @SerialName("genres")
    val genres: List<GenreDto?>? = null,
    @SerialName("homepage")
    val homepage: String? = null,
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
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDto?>? = null,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDto?>? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("revenue")
    val revenue: Int? = null,
    @SerialName("runtime")
    val runtime: Int? = null,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto?>? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("tagline")
    val tagline: String? = null,
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
            imdbId = imdbId.orEmpty()
        )
}