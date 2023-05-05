package com.santukis.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.santukis.entities.movies.Movie
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.viewmodels.core.entities.MovieSection
import com.santukis.viewmodels.home.entities.HomeState

@Composable
fun HomeContent(
    modifier: Modifier,
    homeState: HomeState,
    navigateTo: (DestinationArguments) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        addHighlight(
            modifier = modifier,
            movies = homeState.nowPlayingMovies,
            navigateTo = navigateTo
        )

        homeState.getSections().forEach { section ->
            addSection(
                modifier = modifier,
                movies = section.second,
                section = section.first,
                navigateTo = navigateTo
            )
        }
    }
}

private fun LazyListScope.addHighlight(
    modifier: Modifier,
    movies: List<Movie>,
    navigateTo: (DestinationArguments) -> Unit
) {
    addItem(movies) {
        NowPlayingContent(
            modifier = modifier,
            nowPlayingMovies = movies,
            navigateTo = navigateTo
        )
    }
}

private fun LazyListScope.addSection(
    modifier: Modifier,
    movies: List<Movie>,
    section: MovieSection,
    navigateTo: (DestinationArguments) -> Unit
) {
    addItem(movies) {
        MovieSectionContent(
            modifier = modifier,
            movies = movies,
            section = section,
            navigateTo = navigateTo
        )
    }
}

private fun LazyListScope.addItem(
    movies: List<Movie>,
    widget: @Composable () -> Unit
) {
    if (movies.isNotEmpty()) {
        item {
            widget()
        }
    }
}