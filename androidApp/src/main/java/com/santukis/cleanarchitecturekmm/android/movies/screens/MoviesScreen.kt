package com.santukis.cleanarchitecturekmm.android.movies.screens

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.santukis.cleanarchitecturekmm.android.core.entities.navigation.destinations.Destination
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent
import com.santukis.cleanarchitecturekmm.android.core.events.RequestDecorFitsSystemWindowsChange
import com.santukis.cleanarchitecturekmm.android.movies.components.MoviesContent
import com.santukis.viewmodels.movies.MoviesViewModel

@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel,
    onUiEvent: (OnUiEvent) -> Unit = {},
    onNavigateTo: (Destination) -> Unit = {}
) {
    val homeState = moviesViewModel.moviesState.collectAsState()

    LaunchedEffect(moviesViewModel) {
        if (homeState.value.shouldUpdateData()) {
            moviesViewModel.loadHomeData()
        }

        onUiEvent(RequestDecorFitsSystemWindowsChange(
            decorFitsSystemWindows = false,
            statusBarColor = Color.TRANSPARENT
        ))
    }

    MoviesContent(
        modifier = Modifier
            .fillMaxSize(),
        moviesState = homeState.value,
        onNavigateTo = onNavigateTo
    )
}