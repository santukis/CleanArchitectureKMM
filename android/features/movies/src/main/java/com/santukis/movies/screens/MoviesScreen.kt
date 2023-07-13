package com.santukis.movies.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.santukis.core.entities.getSectionTitle
import com.santukis.movies.widgets.MoviesContent
import com.santukis.navigation.NavigationArguments
import com.santukis.navigation.PopBackStack
import com.santukis.theme.statusBarColor
import com.santukis.viewmodels.core.entities.MovieSection
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.movies.MoviesViewModel
import com.santukis.widgets.insets.rememberStatusBarState

@Composable
fun MoviesScreen(
    section: MovieSection,
    moviesViewModel: MoviesViewModel,
    navigateTo: (NavigationArguments) -> Unit = {}
) {

    val onUiEvent: (OnUiEvent) -> Unit = remember {
        { uiEvent ->
            moviesViewModel.onUiEvent(uiEvent)
        }
    }

    val statusBarState = rememberStatusBarState()

    statusBarState.changesStatusBarColor(
        decorFitsSystemWindows = false,
        statusBarColor = MaterialTheme.statusBarColor().toArgb()
    )

    LaunchedEffect(moviesViewModel) {
        moviesViewModel.loadSectionMovies(section)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = section.getSectionTitle()
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { navigateTo(PopBackStack) },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            )
        },
    ) { paddingValues ->
        MoviesContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            section = section,
            moviesState = moviesViewModel.moviesState.collectAsState().value,
            onUiEvent = onUiEvent,
            navigateTo = navigateTo
        )
    }
}
