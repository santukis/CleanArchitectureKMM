package com.santukis.home.screens

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.santukis.home.widgets.MoviesContent
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.core.events.RequestDecorFitsSystemWindowsChange
import com.santukis.viewmodels.home.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onUiEvent: (OnUiEvent) -> Unit = {},
    navigateTo: (DestinationArguments) -> Unit = {}
) {
    val homeState = homeViewModel.moviesState.collectAsState()

    LaunchedEffect(homeViewModel) {
        if (homeState.value.shouldUpdateData()) {
            homeViewModel.loadHomeData()
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