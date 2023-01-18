package com.santukis.cleanarchitecturekmm.android.movies.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.santukis.viewmodels.movies.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel,
    movieId: String
) {

    val movieDetailState = movieDetailViewModel.movieDetailState.collectAsState()

    LaunchedEffect(true) {
        movieDetailViewModel.loadMovie(movieId)
    }

    MovieDetailContent(
        modifier = Modifier.fillMaxSize(),
        movieDetailState = movieDetailState.value
    )
}