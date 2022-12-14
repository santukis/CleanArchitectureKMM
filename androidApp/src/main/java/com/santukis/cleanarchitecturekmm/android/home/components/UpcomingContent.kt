package com.santukis.cleanarchitecturekmm.android.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.santukis.cleanarchitecturekmm.android.R
import com.santukis.entities.movies.Movie

@Composable
fun UpcomingContent(
    modifier: Modifier,
    upcomingMovies: List<Movie>
) {
    HomeSectionContent(
        modifier = modifier,
        movies = upcomingMovies,
        sectionTitle = stringResource(id = R.string.upcoming)
    )
}
