package com.santukis.moviedetail.stateholders

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.santukis.theme.statusBarColor
import com.santukis.viewmodels.moviedetail.entities.MovieDetailState

@Composable
internal fun rememberMovieDetailScreenState(
    movieDetailState: State<MovieDetailState>,
    statusBarColor: Color = MaterialTheme.statusBarColor()
) = remember(
    movieDetailState,
    statusBarColor
) {
    MovieDetailStateHolder(
        movieDetailState = movieDetailState,
        statusBarColor = statusBarColor
    )
}

internal class MovieDetailStateHolder(
    private val movieDetailState: State<MovieDetailState>,
    private val statusBarColor: Color
) {

    fun getMovieDetailState(): MovieDetailState = movieDetailState.value

    fun getMovieTitle(): String = movieDetailState.value.movie?.titles?.title.orEmpty()

    fun statusBarColorAsInt(): Int = statusBarColor.toArgb()
}