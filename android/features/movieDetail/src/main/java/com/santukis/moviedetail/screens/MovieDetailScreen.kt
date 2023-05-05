package com.santukis.moviedetail.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import com.santukis.moviedetail.stateholders.rememberMovieDetailScreenState
import com.santukis.moviedetail.widgets.MovieDetailContent
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.navigation.destination.arguments.PopBackStackDestinationArguments
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.core.events.RequestDecorFitsSystemWindowsChange
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieId: String,
    movieDetailViewModel: MovieDetailViewModel,
    onUiEvent: (OnUiEvent) -> Unit = {},
    navigateTo: (DestinationArguments) -> Unit = {}
) {

    val movieDetailStateHolder = rememberMovieDetailScreenState(
        movieDetailState = movieDetailViewModel.movieDetailState.collectAsState()
    )

    LaunchedEffect(true) {
        movieDetailViewModel.loadMovie(movieId)

        onUiEvent(
            RequestDecorFitsSystemWindowsChange(
                decorFitsSystemWindows = true,
                statusBarColor = movieDetailStateHolder.statusBarColorAsInt()
            )
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movieDetailStateHolder.getMovieTitle()
                    )
                },
                navigationIcon = { 
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { navigateTo(PopBackStackDestinationArguments()) },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            )
        },
    ) { paddingValues ->
        MovieDetailContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            movieDetailState = movieDetailStateHolder.getMovieDetailState()
        )
    }
}