package com.santukis.cleanarchitecturekmm.android.movies.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.Destination
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.PopBackStackDestination
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent
import com.santukis.cleanarchitecturekmm.android.core.events.RequestDecorFitsSystemWindowsChange
import com.santukis.cleanarchitecturekmm.android.movies.components.MovieDetailContent
import com.santukis.viewmodels.movies.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieId: String,
    movieDetailViewModel: MovieDetailViewModel,
    onUiEvent: (OnUiEvent) -> Unit = {},
    onNavigateTo: (Destination) -> Unit = {}
) {

    val movieDetailState = movieDetailViewModel.movieDetailState.collectAsState()

    LaunchedEffect(true) {
        movieDetailViewModel.loadMovie(movieId)

        onUiEvent(RequestDecorFitsSystemWindowsChange(
            decorFitsSystemWindows = true,
            statusBarColor = android.graphics.Color.BLACK
        ))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movieDetailState.value.movie?.titles?.title.orEmpty(),
                        color = Color.White
                    )
                },
                navigationIcon = { 
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { onNavigateTo(PopBackStackDestination()) },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                backgroundColor = Color.Black
            )
        },
        backgroundColor = Color.Black
    ) { paddingValues ->
        MovieDetailContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            movieDetailState = movieDetailState.value
        )
    }
}