package com.santukis.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        modifier = modifier
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        homeState.nowPlayingMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                NowPlayingContent(
                    modifier = modifier,
                    nowPlayingMovies = movies,
                    navigateTo = navigateTo
                )
            }
        }

        homeState.upcomingMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                MovieSectionContent(
                    modifier = modifier,
                    movies = movies,
                    section = MovieSection.UpcomingMovies,
                    navigateTo = navigateTo
                )
            }
        }

        homeState.popularMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                MovieSectionContent(
                    modifier = modifier,
                    movies = movies,
                    section = MovieSection.PopularMovies,
                    navigateTo = navigateTo
                )
            }
        }

        homeState.couldLikeMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                MovieSectionContent(
                    modifier = modifier,
                    movies = movies,
                    section = MovieSection.CouldYouLikeMovies,
                    navigateTo = navigateTo
                )
            }
        }
    }
}