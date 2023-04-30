package com.santukis.movies.screens

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.core.events.RequestDecorFitsSystemWindowsChange
import com.santukis.viewmodels.movies.MoviesViewModel

@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel,
    onUiEvent: (OnUiEvent) -> Unit = {},
    navigateTo: (DestinationArguments) -> Unit = {}
) {

    LaunchedEffect(true) {
        onUiEvent(
            RequestDecorFitsSystemWindowsChange(
                decorFitsSystemWindows = true,
                statusBarColor = Color.BLACK
            )
        )
    }
}