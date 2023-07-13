package com.santukis.home.screens

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.santukis.home.widgets.HomeContent
import com.santukis.navigation.NavigationArguments
import com.santukis.viewmodels.home.HomeViewModel
import com.santukis.widgets.insets.rememberStatusBarState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigateTo: (NavigationArguments) -> Unit = {}
) {
    val homeState = homeViewModel.homeState.collectAsState()
    val statusBarState = rememberStatusBarState()

    statusBarState.changesStatusBarColor(
        decorFitsSystemWindows = false,
        statusBarColor = Color.TRANSPARENT
    )

    LaunchedEffect(homeViewModel) {
        if (homeState.value.shouldUpdateData()) {
            homeViewModel.loadHomeData()
        }
    }

    HomeContent(
        modifier = Modifier
            .fillMaxSize(),
        homeState = homeState.value,
        navigateTo = navigateTo
    )
}
