package com.santukis.cleanarchitecturekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.rememberNavController
import com.santukis.cleanarchitecturekmm.android.screens.MainScreen
import com.santukis.theme.MovieTheme
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.core.events.RequestDecorFitsSystemWindowsChange

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
        val insetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        if (event.decorFitsSystemWindows) {
            insetsController.show(WindowInsetsCompat.Type.statusBars())
        } else {
            insetsController.hide(WindowInsetsCompat.Type.statusBars())
        }

        window.statusBarColor = event.statusBarColor
    }
}
