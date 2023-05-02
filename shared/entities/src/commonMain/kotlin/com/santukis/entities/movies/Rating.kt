package com.santukis.entities.movies

data class Rating(
    val average: Double,
    val count: Int
) {

    fun getText(): String = "$average (${if (count > 1000) "${count / 1000}k" else count})"

    fun isRated(): Boolean = count > 0
}
