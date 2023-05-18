package com.santukis.widgets.stateholders

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import me.saket.telephoto.zoomable.ZoomableState
import me.saket.telephoto.zoomable.rememberZoomableState

@Composable
fun rememberMoviePosterState(
    zoomableState: ZoomableState = rememberZoomableState(),
    toColor: Color = MaterialTheme.colors.background,
    blendMode: BlendMode = if (isSystemInDarkTheme()) BlendMode.Multiply else BlendMode.Screen
): MoviePosterStateHolder =
    remember(toColor, blendMode) {
        MoviePosterStateHolder(
            zoomableState = zoomableState,
            blendMode = blendMode,
            gradientColors =  listOf(Color.Transparent, toColor)
        )
    }

class MoviePosterStateHolder(
    val zoomableState: ZoomableState,
    val blendMode: BlendMode,
    val gradientColors: List<Color>
)