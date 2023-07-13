package com.santukis.widgets.insets

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class StatusBarState {

    @Composable
    fun changesStatusBarColor(
        decorFitsSystemWindows: Boolean,
        statusBarColor: Int
    ) {
        (LocalContext.current as? Activity)?.window?.let { window ->
            val insetsController =
                WindowCompat.getInsetsController(window, window.decorView)

            if (decorFitsSystemWindows) {
                insetsController.show(WindowInsetsCompat.Type.statusBars())
            } else {
                insetsController.hide(WindowInsetsCompat.Type.statusBars())
            }

            window.statusBarColor = statusBarColor
        }
    }
}

@Composable
fun rememberStatusBarState(): StatusBarState =
    remember {
        StatusBarState()
    }
