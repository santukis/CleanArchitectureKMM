package com.santukis.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.santukis.entities.movies.*
import com.santukis.navigation.destination.DestinationArguments
import com.santukis.navigation.destination.arguments.MovieDetailDestinationArguments
import com.santukis.theme.WhiteTransparent
import com.santukis.widgets.movies.MovieHeader
import com.santukis.widgets.movies.MoviePoster
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.delay

@Composable
@OptIn(ExperimentalSnapperApi::class)
fun NowPlayingContent(
    modifier: Modifier,
    nowPlayingMovies: List<Movie>,
    navigateTo: (DestinationArguments) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(5000)

            if (listState.firstVisibleItemIndex == nowPlayingMovies.lastIndex) {
                listState.scrollToItem(0)

            } else {
                listState.animateScrollToItem(listState.firstVisibleItemIndex + 1)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = (LocalConfiguration.current.screenHeightDp * 0.7).dp),
    ) {
        LazyRow(
            state = listState,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState = listState),
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
                                MovieDetailDestinationArguments(
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
                        modifier =  Modifier
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
                val color = if (listState.firstVisibleItemIndex == index) Color.White else WhiteTransparent

                Icon(
                    imageVector = Icons.Filled.Circle,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .width(8.dp),
                    tint = color
                )
            }
        }
    }
}