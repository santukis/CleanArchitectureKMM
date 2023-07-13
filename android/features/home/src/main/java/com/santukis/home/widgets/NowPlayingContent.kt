package com.santukis.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.santukis.entities.movies.Movie
import com.santukis.home.navigation.arguments.ToMovieDetailDestinationArguments
import com.santukis.home.stateholders.rememberNowPlayingContentState
import com.santukis.navigation.NavigationArguments
import com.santukis.widgets.movies.MovieHeader
import com.santukis.widgets.movies.MoviePoster

@SuppressWarnings("LongMethod")
@Composable
fun NowPlayingContent(
    modifier: Modifier,
    nowPlayingMovies: List<Movie>,
    navigateTo: (NavigationArguments) -> Unit
) {
    val nowPlayingContentHolder = rememberNowPlayingContentState(listSize = nowPlayingMovies.size)

    LaunchedEffect(key1 = nowPlayingMovies) {
        nowPlayingContentHolder.startScrollAnimation()
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = (LocalConfiguration.current.screenHeightDp * 0.7).dp),
    ) {
        LazyRow(
            state = nowPlayingContentHolder.listState,
            flingBehavior = nowPlayingContentHolder.flingBehavior,
        ) {
            items(
                items = nowPlayingMovies,
                key = { movie -> movie.id }
            ) { movie ->
                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable {
                            navigateTo(
                                ToMovieDetailDestinationArguments(
                                    movieId = movie.id
                                )
                            )
                        }
                ) {
                    MoviePoster(
                        modifier = Modifier
                            .fillParentMaxWidth(),
                        movie = movie
                    )

                    MovieHeader(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(vertical = 32.dp),
                        movie = movie
                    )
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp)
        ) {
            items(nowPlayingMovies.size) { index ->
                Icon(
                    imageVector = Icons.Filled.Circle,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .width(8.dp),
                    tint = nowPlayingContentHolder.getColorForPosition(index)
                )
            }
        }
    }
}
