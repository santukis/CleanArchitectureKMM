package com.santukis.cleanarchitecturekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.santukis.injection.DependencyInjector

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = DependencyInjector.initialize().moviesViewModel()

        setContent {
            val movieState = viewModel?.observeMovieDetail()?.collectAsState()

            Text(
                text = movieState?.value?.movie?.id?.toString().orEmpty(),
                color = Color.White
            )
        }

        viewModel?.loadMovie("500")
    }
}
