package com.santukis.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

        addSection(
            modifier = modifier,
            movies = homeState.upcomingMovies,
            section = MovieSection.UpcomingMovies,
            navigateTo = navigateTo
        )

        addSection(
            modifier = modifier,
            movies = homeState.popularMovies,
            section = MovieSection.PopularMovies,
            navigateTo = navigateTo
        )

        addSection(
            modifier = modifier,
            movies = homeState.couldLikeMovies,
            section = MovieSection.CouldYouLikeMovies,
            navigateTo = navigateTo
        )
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
    movies.takeIf { it.isNotEmpty() }?.let {
        item {
            widget()
        }
    }
}