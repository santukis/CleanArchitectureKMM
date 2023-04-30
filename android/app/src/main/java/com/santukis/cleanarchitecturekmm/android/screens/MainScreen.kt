package com.santukis.cleanarchitecturekmm.android.screens

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.santukis.navigation.bottombar.BottomNavigationAnimatedVisibility
import com.santukis.cleanarchitecturekmm.android.navigation.MovieNavHost
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.HomeDestination
import com.santukis.cleanarchitecturekmm.android.navigation.destinations.ShowsDestination
import com.santukis.viewmodels.core.events.OnUiEvent

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    onUiEvent: (OnUiEvent) -> Unit
) {

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        scaffoldState = rememberScaffoldState(),
        backgroundColor = Color.Black,
        bottomBar = {
            BottomNavigationAnimatedVisibility(
                navController = navController,
                bottomNavigationDestinations = listOf(
                    HomeDestination(),
                    ShowsDestination()
                )
            )
        }
    ) { paddingValues ->
        MovieNavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            onUiEvent = onUiEvent
        )
    }
}