package com.santukis.cleanarchitecturekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.santukis.cleanarchitecturekmm.android.screens.MainScreen
import com.santukis.navigation.movies.arguments.MoviesArgumentMapper
import com.santukis.navigation.movies.graph.MoviesAppGraph
import com.santukis.navigation.rememberRouter
import com.santukis.theme.MovieTheme

class MovieDatabaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieTheme {
                val router = rememberRouter(
                    argumentsMapper = remember { MoviesArgumentMapper() },
                    navController = rememberNavController()
                )
                val moviesAppGraph = remember { MoviesAppGraph() }

                MainScreen(router, moviesAppGraph)
            }
        }
    }
}
