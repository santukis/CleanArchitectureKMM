package com.santukis.widgets.movies

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.PosterSize
import com.santukis.widgets.stateholders.MoviePosterStateHolder
import com.santukis.widgets.stateholders.rememberMoviePosterState

@Composable
fun MoviePoster(
    movie: Movie,
    modifier: Modifier = Modifier
) {

    val moviePosterStateHolder = rememberMoviePosterState()

    AsyncImage(
        model = movie.images.posterImage?.getUrl(PosterSize.W_342),
        contentDescription = "",
        modifier = modifier
            .drawWithCache {
                drawVerticalGradient(moviePosterStateHolder)
            },
        contentScale = ContentScale.Crop
    )
}

private fun CacheDrawScope.drawVerticalGradient(
    moviePosterStateHolder: MoviePosterStateHolder
): DrawResult {
    val gradient = Brush.verticalGradient(
        colors = moviePosterStateHolder.gradientColors,
        startY = 0f,
        endY = size.height
    )
    return onDrawWithContent {
        drawContent()
        drawRect(
            gradient,
            blendMode = moviePosterStateHolder.blendMode
        )
    }
}