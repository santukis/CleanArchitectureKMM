package com.santukis.cleanarchitecturekmm.android.home.components

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
import com.santukis.cleanarchitecturekmm.android.core.entities.destinations.Destination
import com.santukis.cleanarchitecturekmm.android.core.entities.destinations.MovieDetailDestination
import com.santukis.viewmodels.home.HomeViewModel
import com.santukis.viewmodels.home.entities.HomeState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigateTo: (Destination<*>) -> Unit
) {
    val homeState = homeViewModel.homeState.collectAsState()

    LaunchedEffect(true) {
        homeViewModel.loadHomeData()
    }

    HomeContent(
        modifier = Modifier
            .fillMaxSize(),
        homeState = homeState.value,
        onNavigateTo = onNavigateTo
    )
}

@Composable
fun HomeContent(
    modifier: Modifier,
    homeState: HomeState,
    onNavigateTo: (Destination<*>) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            NowPlayingContent(
                modifier = modifier,
                nowPlayingMovies = homeState.nowPlayingMovies,
                onNavigateTo = onNavigateTo
            )
        }

        item {
            HomeSectionContent(
                modifier = modifier,
                movies = homeState.upcomingMovies,
                sectionTitle = stringResource(id = R.string.upcoming)
            ) { movie ->
                onNavigateTo(MovieDetailDestination(movie.id))
            }
        }

        item {
            HomeSectionContent(
                modifier = modifier,
                movies = homeState.popularMovies,
                sectionTitle = stringResource(id = R.string.popular)
            ) { movie ->
                onNavigateTo(MovieDetailDestination(movie.id))
            }
        }
    }
}