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
import androidx.navigation.compose.NavHost
import com.santukis.cleanarchitecturekmm.android.navigation.arguments.ToHomeScreen
import com.santukis.cleanarchitecturekmm.android.navigation.arguments.ToTvShowsScreen
import com.santukis.cleanarchitecturekmm.android.widgets.MoviesBottomNavigation
import com.santukis.navigation.Router
import com.santukis.navigation.movies.graph.MoviesAppGraph

@Composable
fun MainScreen(
    router: Router,
    moviesAppGraph: MoviesAppGraph
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            modifier = Modifier.navigationBarsPadding(),
            scaffoldState = rememberScaffoldState(),
            bottomBar = {
                MoviesBottomNavigation(
                    router = router,
                    destinationArguments = listOf(
                        ToHomeScreen,
                        ToTvShowsScreen
                    )
                )
            }
        ) { paddingValues ->
            NavHost(
                modifier = Modifier
                    .padding(paddingValues),
                navController = router.navController,
                startDestination = moviesAppGraph.getStartDestination()
            ) {
                with(moviesAppGraph) {
                    buildGraph(router)
                }
            }
        }
    }
}
