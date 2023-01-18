package com.santukis.cleanarchitecturekmm.android.movies.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.santukis.viewmodels.movies.entities.MovieDetailState

@Composable
fun MovieDetailContent(
    modifier: Modifier,
    movieDetailState: MovieDetailState
) {

    Box(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = movieDetailState.movie?.titles?.title ?: "No Title",
            color = Color.White
        )
    }

}
