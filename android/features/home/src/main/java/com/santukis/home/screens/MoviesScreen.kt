package com.santukis.home.screens

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.santukis.uievents.OnUiEvent
import com.santukis.home.widgets.MoviesContent
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.uievents.events.RequestDecorFitsSystemWindowsChange
import com.santukis.viewmodels.movies.MoviesViewModel

@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel,
    onUiEvent: (OnUiEvent) -> Unit = {},
    navigateTo: (DestinationArguments) -> Unit = {}
) {
    val homeState = moviesViewModel.moviesState.collectAsState()

    LaunchedEffect(moviesViewModel) {
        if (homeState.value.shouldUpdateData()) {
            moviesViewModel.loadHomeData()
        }

        onUiEvent(
            RequestDecorFitsSystemWindowsChange(
            decorFitsSystemWindows = false,
            statusBarColor = Color.TRANSPARENT
        )
        )
    }

    MoviesContent(
        modifier = Modifier
            .fillMaxSize(),
        moviesState = homeState.value,
        navigateTo = navigateTo
    )
}