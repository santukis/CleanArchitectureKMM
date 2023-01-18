package com.santukis.cleanarchitecturekmm.android.home.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.santukis.cleanarchitecturekmm.android.core.components.MovieNavHost
import com.santukis.cleanarchitecturekmm.android.theme.MovieTheme
import com.santukis.injection.getDependencyInjector

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MovieTheme {
                MovieNavHost(
                    dependencyInjector = getDependencyInjector(application)
                )
            }
        }
    }
}

