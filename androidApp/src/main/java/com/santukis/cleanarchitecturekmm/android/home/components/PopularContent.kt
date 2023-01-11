package com.santukis.cleanarchitecturekmm.android.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.santukis.cleanarchitecturekmm.android.R
import com.santukis.entities.movies.Movie

@Composable
fun PopularContent(
    modifier: Modifier,
    popularMovies: List<Movie>
) {
    HomeSectionContent(
        modifier = modifier,
        movies = popularMovies,
        sectionTitle = stringResource(id = R.string.popular)
    )
}
