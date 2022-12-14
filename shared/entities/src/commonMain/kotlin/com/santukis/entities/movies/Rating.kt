package com.santukis.entities.movies

data class Rating(
    val average: Double,
    val count: Int
) {

    fun getText(): String = "$average ($count)"
}
