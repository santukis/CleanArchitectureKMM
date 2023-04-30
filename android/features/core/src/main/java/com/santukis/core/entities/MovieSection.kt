package com.santukis.core.entities

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.santukis.core.R
import com.santukis.viewmodels.core.entities.MovieSection

@Composable
fun MovieSection.getSectionTitle(): String = when(this) {
    MovieSection.UpcomingMovies -> stringResource(id = R.string.upcoming)
    MovieSection.PopularMovies -> stringResource(id = R.string.popular)
    MovieSection.CouldYouLikeMovies ->  stringResource(id = R.string.could_like)
    else -> ""
}