package com.santukis.widgets.movies

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
    modifier: Modifier,
    movie: Movie
) {

    AsyncImage(
        model = movie.images.posterImage?.getUrl(PosterSize.W_342),
        contentDescription = "",
        modifier = modifier
            .drawWithCache {
                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                    startY = 0f,
                    endY = size.height
                )
                onDrawWithContent {
                    drawContent()
                    drawRect(
                        gradient,
                        blendMode = BlendMode.Multiply
                    )
                }
            },
        contentScale = ContentScale.Crop
    )
}