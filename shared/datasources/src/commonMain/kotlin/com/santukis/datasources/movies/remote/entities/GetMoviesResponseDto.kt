package com.santukis.datasources.movies.remote.entities


import com.santukis.entities.movies.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMoviesResponseDto(
    @SerialName("dates")
    val dates: DatesDto? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<ResultDto?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
) {

    fun toMovies(): List<Movie> =
        results?.mapNotNull { it?.toMovie() }.orEmpty()
}