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
import androidx.compose.ui.unit.dp
import com.santukis.viewmodels.movies.HomeViewModel
import com.santukis.viewmodels.movies.entities.HomeState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val homeState = homeViewModel.homeState.collectAsState()

    LaunchedEffect(true) {
        homeViewModel.loadHomeData()
    }

    HomeContent(
        modifier = Modifier
            .fillMaxSize(),
        homeState = homeState.value
    )
}

@Composable
fun HomeContent(
    modifier: Modifier,
    homeState: HomeState
) {
    LazyColumn(
        modifier = modifier
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            NowPlayingContent(
                modifier = modifier,
                nowPlayingMovies = homeState.nowPlayingMovies
            )
        }

        item {
            UpcomingContent(
                modifier = modifier,
                upcomingMovies = homeState.upcomingMovies
            )
        }
    }
}