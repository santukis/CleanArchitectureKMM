package com.santukis.cleanarchitecturekmm.android.home.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.santukis.cleanarchitecturekmm.android.home.components.HomeScreen
import com.santukis.cleanarchitecturekmm.android.theme.MovieTheme
import com.santukis.injection.getDependencyInjector
import com.santukis.viewmodels.movies.MovieViewModel

class HomeActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by lazy {
        getDependencyInjector(application).moviesViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                HomeScreen(movieViewModel = viewModel)
            }
        }

        viewModel.loadNowPlayingMovies()
    }
}

