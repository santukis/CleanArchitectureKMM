package com.santukis.movies.screens

import android.graphics.Color
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
import com.santukis.core.entities.getSectionTitle
import com.santukis.movies.widgets.MoviesContent
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.navigation.destination.arguments.PopBackStackDestinationArguments
import com.santukis.viewmodels.core.entities.MovieSection
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.core.events.RequestDecorFitsSystemWindowsChange
import com.santukis.viewmodels.movies.MoviesViewModel

@Composable
fun MoviesScreen(
    section: MovieSection,
    moviesViewModel: MoviesViewModel,
    onUiEvent: (OnUiEvent) -> Unit = {},
    navigateTo: (DestinationArguments) -> Unit = {}
) {

    val localOnUiEvent: (OnUiEvent) -> Unit = { uiEvent ->
        moviesViewModel.onUiEvent(uiEvent)
        onUiEvent(uiEvent)
    }

    LaunchedEffect(moviesViewModel) {
        moviesViewModel.loadSectionMovies(section)

        onUiEvent(
            RequestDecorFitsSystemWindowsChange(
                decorFitsSystemWindows = true,
                statusBarColor = Color.BLACK
            )
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = section.getSectionTitle(),
                        color = androidx.compose.ui.graphics.Color.White
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { navigateTo(PopBackStackDestinationArguments()) },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                },
                backgroundColor = androidx.compose.ui.graphics.Color.Black
            )
        },
        backgroundColor = androidx.compose.ui.graphics.Color.Black
    ) { paddingValues ->
        MoviesContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            section = section,
            moviesState = moviesViewModel.moviesState.collectAsState().value,
            onUiEvent = localOnUiEvent,
            navigateTo = navigateTo
        )
    }
}