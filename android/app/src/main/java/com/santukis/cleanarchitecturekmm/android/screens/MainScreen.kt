package com.santukis.cleanarchitecturekmm.android.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.santukis.navigation.bottombar.BottomNavigationAnimatedVisibility
import com.santukis.navigation.destinations.HomeDestination
import com.santukis.navigation.destinations.ShowsDestination
import com.santukis.navigation.graphs.MovieNavHost
import com.santukis.viewmodels.core.events.OnUiEvent

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    onUiEvent: (OnUiEvent) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            modifier = Modifier.navigationBarsPadding(),
            scaffoldState = rememberScaffoldState(),
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
}
