package com.santukis.cleanarchitecturekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.santukis.cleanarchitecturekmm.android.screens.MainScreen
import com.santukis.uievents.OnUiEvent
import com.santukis.uievents.events.RequestDecorFitsSystemWindowsChange
import com.santukis.theme.MovieTheme

class MovieDatabaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieTheme {
                val navController = rememberNavController()
                MainScreen(navController) { event -> manageOnUiEventLaunched(event) }
            }
        }
    }

    private fun manageOnUiEventLaunched(event: OnUiEvent) {
        when (event) {
            is RequestDecorFitsSystemWindowsChange -> changesStatusBarColor(event)
        }
    }

    private fun changesStatusBarColor(event: RequestDecorFitsSystemWindowsChange) {
        WindowCompat.setDecorFitsSystemWindows(window, event.decorFitsSystemWindows)
        window.statusBarColor = event.statusBarColor
    }
}

