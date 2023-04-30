package com.santukis.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.santukis.home.R
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.viewmodels.home.entities.MoviesState

@Composable
fun MoviesContent(
    modifier: Modifier,
    moviesState: MoviesState,
    navigateTo: (DestinationArguments) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        moviesState.nowPlayingMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                NowPlayingContent(
                    modifier = modifier,
                    nowPlayingMovies = movies,
                    navigateTo = navigateTo
                )
            }
        }

        moviesState.upcomingMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                MovieSectionContent(
                    modifier = modifier,
                    movies = movies,
                    sectionTitle = stringResource(id = R.string.upcoming),
                    navigateTo = navigateTo
                )
            }
        }

        moviesState.popularMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                MovieSectionContent(
                    modifier = modifier,
                    movies = movies,
                    sectionTitle = stringResource(id = R.string.popular),
                    navigateTo = navigateTo
                )
            }
        }

        moviesState.couldLikeMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                MovieSectionContent(
                    modifier = modifier,
                    movies = movies,
                    sectionTitle = stringResource(id = R.string.could_like),
                    navigateTo = navigateTo
                )
            }
        }
    }
}