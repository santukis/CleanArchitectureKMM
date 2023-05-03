package com.santukis.widgets.movies

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.PosterSize

@Composable
fun MoviePoster(
    movie: Movie,
    modifier: Modifier = Modifier
) {

    val toColor = MaterialTheme.colors.background
    val blendMode = if (isSystemInDarkTheme()) BlendMode.Multiply else BlendMode.Screen

    AsyncImage(
        model = movie.images.posterImage?.getUrl(PosterSize.W_342),
        contentDescription = "",
        modifier = modifier
            .drawWithCache {
                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, toColor),
                    startY = 0f,
                    endY = size.height
                )
                onDrawWithContent {
                    drawContent()
                    drawRect(
                        gradient,
                        blendMode = blendMode
                    )
                }
            },
        contentScale = ContentScale.Crop
    )
}