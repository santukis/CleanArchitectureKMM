package com.santukis.cleanarchitecturekmm.android.home.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.santukis.cleanarchitecturekmm.android.core.events.OnUiEvent
import com.santukis.cleanarchitecturekmm.android.core.events.RequestDecorFitsSystemWindowsChange
import com.santukis.cleanarchitecturekmm.android.home.screens.HomeScreen
import com.santukis.cleanarchitecturekmm.android.theme.MovieTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieTheme {
                HomeScreen { event -> manageOnUiEventLaunched(event) }
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

