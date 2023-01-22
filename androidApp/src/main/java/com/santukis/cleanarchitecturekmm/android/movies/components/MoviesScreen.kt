package com.santukis.cleanarchitecturekmm.android.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.santukis.cleanarchitecturekmm.android.R
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.Destination
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.MovieDetailDestination
import com.santukis.cleanarchitecturekmm.android.home.components.NowPlayingContent
import com.santukis.viewmodels.movies.MoviesViewModel
import com.santukis.viewmodels.movies.entities.MoviesState

@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel,
    onNavigateTo: (Destination) -> Unit
) {
    val homeState = moviesViewModel.moviesState.collectAsState()

    LaunchedEffect(moviesViewModel) {
        if (homeState.value.shouldUpdateData()) {
            moviesViewModel.loadHomeData()
        }
    }

    MoviesContent(
        modifier = Modifier
            .fillMaxSize(),
        moviesState = homeState.value,
        onNavigateTo = onNavigateTo
    )
}

@Composable
fun MoviesContent(
    modifier: Modifier,
    moviesState: MoviesState,
    onNavigateTo: (Destination) -> Unit
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
                    onNavigateTo = onNavigateTo
                )
            }
        }

        moviesState.upcomingMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                MovieSectionContent(
                    modifier = modifier,
                    movies = movies,
                    sectionTitle = stringResource(id = R.string.upcoming)
                ) { movie ->
                    onNavigateTo(MovieDetailDestination(movie.id))
                }
            }
        }

        moviesState.popularMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                MovieSectionContent(
                    modifier = modifier,
                    movies = movies,
                    sectionTitle = stringResource(id = R.string.popular)
                ) { movie ->
                    onNavigateTo(MovieDetailDestination(movie.id))
                }
            }
        }

        moviesState.couldLikeMovies.takeIf { it.isNotEmpty() }?.let { movies ->
            item {
                MovieSectionContent(
                    modifier = modifier,
                    movies = movies,
                    sectionTitle = stringResource(id = R.string.could_like)
                ) { movie ->
                    onNavigateTo(MovieDetailDestination(movie.id))
                }
            }
        }
    }
}