package com.santukis.cleanarchitecturekmm.android.home.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.santukis.cleanarchitecturekmm.android.home.components.HomeScreen
import com.santukis.cleanarchitecturekmm.android.theme.MovieTheme
import com.santukis.injection.getDependencyInjector
import com.santukis.viewmodels.movies.HomeViewModel

class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by lazy {
        getDependencyInjector(application).homeViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MovieTheme {
                HomeScreen(homeViewModel = viewModel)
            }
        }
    }
}

